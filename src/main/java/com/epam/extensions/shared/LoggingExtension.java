package com.epam.extensions.shared;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Log4j2
public class LoggingExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("Starting @Test {}", context.getRequiredTestMethod().getName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String testMethodName = context.getRequiredTestMethod().getName();
        context.getExecutionException().ifPresentOrElse(exception ->
                        log.warn("@Test {} is failed. Error stacktrace:\n{}", testMethodName, exception.getMessage()),
                () -> log.info("@Test {} is passed", testMethodName));
    }
}
