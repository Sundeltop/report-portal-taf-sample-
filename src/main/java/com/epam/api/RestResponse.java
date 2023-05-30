package com.epam.api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.function.Function;

public class RestResponse<T> {

    private final Response response;
    private final Function<Response, T> extractor;

    public RestResponse(Response response, Function<Response, T> extractor) {
        this.response = response;
        this.extractor = extractor;
    }

    public T extract() {
        return extractor.apply(response);
    }

    public ValidatableResponse validate() {
        return response.then();
    }
}
