package com.company.movieticket.service;

import com.company.movieticket.dto.MockedRequestResponsePair;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MockService {
    private ResourceLoader resourceLoader;
    private ObjectMapper mapper;

    public MockService(ObjectMapper mapper, ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    public ResponseEntity<JsonNode> getResponse(
            String operationName, HttpServletRequest request, Object requestBody) {
        Map<String, String> headers = getHeaders(request);
        String urlPathWithQuery = getUrlPathWithQuery(request);

        Resource resource = resourceLoader.getResource("classpath:mock/" + operationName + ".json");
        try {
            List<MockedRequestResponsePair> pairs =
                    mapper.readValue(
                            resource.getContentAsString(StandardCharsets.UTF_8),
                            new TypeReference<>() {});
            for (MockedRequestResponsePair pair : pairs) {
                Object expectedRequestBody =
                        mapper.treeToValue(pair.getRequestBody(), requestBody.getClass());
                if (pair.getUrl().equals(urlPathWithQuery)
                        && mapHasValue(headers, pair.getRequestHeaders())
                        && expectedRequestBody.equals(requestBody)) {
                    return ResponseEntity.status(pair.getStatusCode()).body(pair.getResponseBody());
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        throw new IllegalStateException("Cannot find matching mocked request response pair");
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), request::getHeader));
    }

    private String getUrlPathWithQuery(HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        String urlPath = uri.getPath();
        if (request.getQueryString() != null) {
            return urlPath + "?" + request.getQueryString();
        }
        return urlPath;
    }

    private boolean mapHasValue(Map<String, String> actualMap, Map<String, String> expectedMap) {
        for (Map.Entry<String, String> sampleEntry : expectedMap.entrySet()) {
            String actualValue = actualMap.get(sampleEntry.getKey());
            if (actualValue == null) {
                return false;
            }
            if (!actualValue.equals(sampleEntry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
