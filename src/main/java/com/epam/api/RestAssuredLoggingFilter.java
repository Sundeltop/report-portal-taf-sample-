package com.epam.api;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestAssuredLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        log.info(requestSpec.getMethod() + " " + requestSpec.getURI() +
                "\nRequest Body => " + requestSpec.getBody() +
                "\nResponse Status => " + response.getStatusCode() +
                "\nResponse Body => " + response.getBody().prettyPrint());

        return response;
    }
}
