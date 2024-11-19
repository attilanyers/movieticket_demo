package com.company.movieticket.security;

import com.company.movieticket.config.EmailConfigProperties;
import com.company.movieticket.dao.UserDao;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties({JwtKeyConfigProperties.class, EmailConfigProperties.class})
public class SecurityConfig {

    private final JwtKeyConfigProperties jwtKeyConfigProperties;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtKeyConfigProperties jwtKeyConfigProperties, UserDao userDao) {
        this.jwtKeyConfigProperties = jwtKeyConfigProperties;
        this.userDetailsService =
                email ->
                        userDao.findByEmailFetchAuthorities(email)
                                .orElseThrow(
                                        () ->
                                                new UsernameNotFoundException(
                                                        "Cannot find user: " + email));
    }

    @Bean
    public AuthenticationManager authManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        return http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/error/**").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                            auth.requestMatchers(HttpMethod.OPTIONS, "/auth/login").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/auth/requestPasswordReset")
                                    .permitAll();
                            auth.requestMatchers(HttpMethod.OPTIONS, "/auth/requestPasswordReset")
                                    .permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/auth/passwordReset")
                                    .permitAll();
                            auth.requestMatchers(HttpMethod.OPTIONS, "/auth/passwordReset")
                                    .permitAll();

                            auth.anyRequest().authenticated();
                        })
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt((jwt) -> jwt.decoder(jwtDecoder())))
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(jwtKeyConfigProperties.publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk =
                new RSAKey.Builder(jwtKeyConfigProperties.publicKey())
                        .privateKey(jwtKeyConfigProperties.privateKey())
                        .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
