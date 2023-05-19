package com.epam.api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.epam.config.ConfigurationManager.configuration;
import static io.restassured.RestAssured.oauth2;
import static io.restassured.http.ContentType.JSON;

public abstract class BaseRestClient {

    protected final RequestSpecification requestSpecification;

    public BaseRestClient(String accessToken) {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(String.format("%s/api/v1/default_personal", configuration().baseUrl()))
                .setBasePath(getPathToResource())
                .setAuth(oauth2(accessToken))
                .build();
    }

    public abstract String getPathToResource();
}
