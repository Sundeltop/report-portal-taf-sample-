package com.epam.api.tests;

import com.epam.api.RestAssuredLoggingFilter;
import com.epam.api.RestWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;

import static io.restassured.RestAssured.filters;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

//TODO: enable parallel execution for API tests
@Execution(SAME_THREAD)
public class BaseApiTest {

    protected static RestWrapper api;

    @BeforeAll
    static void prepareApiClient() {
        filters(new RestAssuredLoggingFilter());
        api = new RestWrapper();
    }
}
