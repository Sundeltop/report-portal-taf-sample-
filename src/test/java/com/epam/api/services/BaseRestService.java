package com.epam.api.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public abstract class BaseRestService {

    private final RequestSpecification requestSpecification;
    private static final String LOCALHOST_BASE_PATH = "http://localhost:8080/";

    public BaseRestService() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBasePath(LOCALHOST_BASE_PATH)
                .setBasePath(getPathToResource())
                .build();
    }

    public abstract String getPathToResource();

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
