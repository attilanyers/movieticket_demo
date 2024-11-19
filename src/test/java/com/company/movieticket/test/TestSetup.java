package com.company.movieticket.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestSetup implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setContentType(ContentType.JSON)
                        .setAccept(ContentType.JSON)
                        .build();
    }
}
