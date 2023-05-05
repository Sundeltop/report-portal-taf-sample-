package com.epam.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.epam.config.ConfigurationManager.configuration;
import static io.restassured.http.ContentType.JSON;

public abstract class BaseRestClient {

    private final RequestSpecification requestSpecification;

    public BaseRestClient() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(configuration().baseUrl())
                .setBasePath(getPathToResource())
                .build();
    }

    protected abstract String getPathToResource();

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
