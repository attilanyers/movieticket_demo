package com.company.movieticket.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtKeyConfigProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {}
