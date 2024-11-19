package com.company.movieticket.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class MockedRequestResponsePair {
    @NotNull UUID id;
    @NotBlank String url;
    Map<String, String> requestHeaders;
    JsonNode requestBody;
    Map<String, String> responseHeaders;
    JsonNode responseBody;
    int statusCode;
}
