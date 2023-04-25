package com.epam.ui.tests.reportportal;

import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LaunchesTest extends BaseUiTest {

    @ParameterizedTest
    @ValueSource(strings =
            {"TOTAL", "PASSED", "FAILED", "SKIPPED", "PRODUCT BUG", "AUTO BUG", "SYSTEM ISSUE", "TO INVESTIGATE"})
    void checkLaunchesTableContainsExpectedColumn(String column) {
        openPage()
                .loginAsDefaultUser()
                .openLauchesTab()
                .isLaunchesTableContainsColumn(column);
    }
}
