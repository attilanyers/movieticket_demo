package com.company.movieticket.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "email")
public record EmailConfigProperties(@NotBlank String from, @NotBlank String passwordResetHost) {}
