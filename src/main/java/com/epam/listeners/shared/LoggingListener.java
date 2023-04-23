package com.epam.listeners.shared;

import lombok.extern.log4j.Log4j2;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

@Log4j2
public class LoggingListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        log.info("Starting @Test {}", method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        String testMethodName = method.getTestMethod().getMethodName();

        if (testResult.isSuccess()) {
            log.info("@Test {} is passed", testMethodName);
        } else {
            log.warn("@Test {} is failed. Error stacktrace:\n{}", testMethodName, testResult.getThrowable().getMessage());
        }
    }
}
