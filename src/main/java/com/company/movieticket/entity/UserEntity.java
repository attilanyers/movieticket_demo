package com.company.movieticket.entity;

import com.company.movieticket.entity.BaseEntity;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    @NotNull
    private UUID id;

    @Column @Email @NotNull private String email;
    @Column @NotNull private String password;
    @Column private String passwordResetToken;

    @OneToMany(mappedBy = "user")
    private List<UserAuthorityEntity> userAuthorities;

    @OneToMany(mappedBy = "user")
    private List<ReservationEntity> reservations;

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public List<UserAuthorityEntity> getAuthorities() {
        return userAuthorities;
    }

    public boolean hasAnyRole(UserRole... roles) {
        for (UserAuthorityEntity userAuthority : userAuthorities) {
            for (UserRole role : roles) {
                if (role.name().equals(userAuthority.getAuthority())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasRole(UserRole role) {
        return hasAnyRole(role);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
