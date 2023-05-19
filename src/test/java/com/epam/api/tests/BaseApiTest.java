package com.epam.api.tests;

import com.epam.api.RestWrapper;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    protected static RestWrapper api;

    @BeforeAll
    static void prepareApiClient() {
        api = new RestWrapper();
    }
}
