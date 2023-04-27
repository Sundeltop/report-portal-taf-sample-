package com.epam.ui.tests.reportportal;

import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginTest extends BaseUiTest {

    @Test
    void checkLoginWithInvalidCredentials() {
        openPage()
                .login("login", "password")
                .isLoginNotificationDisplayedWithText("An error occurred while connecting to server: You do not have enough permissions. Bad credentials");
    }

    @Test
    void checkLoginWithValidCredentials() {
        openPageAndLoginAsDefaultUser();
    }

    @Test
    void checkForgotPasswordShowsRestoreOption() {
        openPage()
                .forgotPassword()
                .isRestoreOptionAvailable();
    }

    @Test
    void checkCanLoginInTwoBrowsersAtTheSameTime() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> firstSession = runAsync(this::openPageAndLoginAsDefaultUser, newSingleThreadExecutor());
        openPageAndLoginAsDefaultUser();
        firstSession.get(60, SECONDS);
    }
}
