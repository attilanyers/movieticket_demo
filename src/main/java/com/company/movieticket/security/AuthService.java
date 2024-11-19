package com.company.movieticket.security;

import com.company.movieticket.config.EmailConfigProperties;
import com.company.movieticket.dao.UserDao;
import com.company.movieticket.dto.PasswordResetRequest;
import com.company.movieticket.dto.RequestPasswordResetRequest;
import com.company.movieticket.dto.UserLoginRequest;
import com.company.movieticket.dto.UserLoginResponse;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.exception.BaseException;
import com.company.movieticket.exception.ErrorEnum;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import com.company.movieticket.service.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private JwtEncoder jwtEncoder;
    private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private EmailService emailService;
    private EmailConfigProperties emailConfigProperties;
    private PasswordEncoder passwordEncoder;
    private SecureRandom secureRandom = new SecureRandom();

    public AuthService(
            JwtEncoder jwtEncoder,
            AuthenticationManager authenticationManager,
            EmailService emailService,
            EmailConfigProperties emailConfigProperties,
            PasswordEncoder passwordEncoder,
            UserDao userDao) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.emailConfigProperties = emailConfigProperties;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public UserLoginResponse login(UserLoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(), loginRequest.getPassword()));
        String token = generateAuthToken(authentication);
        UserLoginResponse response = new UserLoginResponse();
        response.setToken(token);
        response.setId(UUID.fromString(authentication.getName()));
        response.setAuthorities(
                authentication.getAuthorities().stream().map(a -> a.getAuthority()).toList());
        return response;
    }

    public void requestPasswordReset(RequestPasswordResetRequest passwordResetRequest) {
        Optional<UserEntity> userEntityOpt =
                userDao.findByEmailFetchAuthorities(passwordResetRequest.getEmail());
        UserEntity userEntity = userEntityOpt.orElse(null);
        if (userEntity == null) {
            return;
        }
        String token = generatePasswordResetToken();
        userEntity.setPasswordResetToken(token);
        userDao.save(userEntity);
        String htmlContent =
                "<a href='"
                        + emailConfigProperties.passwordResetHost()
                        + "/auth/resetPassword?token="
                        + "'>Click here to reset password</a>";
        emailService.sendEmail(
                emailConfigProperties.from(),
                passwordResetRequest.getEmail(),
                "Password reset",
                htmlContent);
    }

    public void passwordReset(PasswordResetRequest request) {
        Optional<UserEntity> userEntityOpt =
                userDao.findByEmailFetchAuthorities(request.getEmail());
        UserEntity userEntity =
                userEntityOpt.orElseThrow(() -> new BaseException(ErrorEnum.ENTITY_NOT_FOUND));
        if (!request.getToken().equals(userEntity.getPasswordResetToken())) {
            throw new BaseException(ErrorEnum.UNAUTHORIZED);
        }
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setPasswordResetToken(null);
        userDao.save(userEntity);
    }

    private String generateAuthToken(Authentication authentication) {
        Instant now = Instant.now();
        JwtClaimsSet claims =
                JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(now)
                        .subject(authentication.getName())
                        .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String generatePasswordResetToken() {
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
