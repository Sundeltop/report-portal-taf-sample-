package com.epam.api.tests;

import com.epam.api.RestWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

//TODO: enable parallel execution for API tests
@Execution(SAME_THREAD)
public class BaseApiTest {

    protected static RestWrapper api;

    @BeforeAll
    static void prepareApiClient() {
        api = new RestWrapper();
    }
}
