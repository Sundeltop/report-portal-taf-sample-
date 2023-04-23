package com.epam.ui.tests.reportportal;

import com.epam.ui.pages.reportportal.LoginPage;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.page;

public class LaunchesTest extends BaseUiTest {

    @ParameterizedTest
    @ValueSource(strings =
            {"TOTAL", "PASSED", "FAILED", "SKIPPED", "PRODUCT BUG", "AUTO BUG", "SYSTEM ISSUE", "TO INVESTIGATE"})
    void checkLaunchesTableContainsExpectedColumn(String column) {
        page(LoginPage.class)
                .loginAsDefaultUser()
                .openLauchesTab()
                .isLaunchesTableContainsColumn(column);
    }
}
