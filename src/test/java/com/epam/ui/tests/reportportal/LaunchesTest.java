package com.epam.ui.tests.reportportal;

import com.epam.ui.pages.reportportal.WidgetsPage;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LaunchesTest extends BaseUiTest {

    private static final Integer NEGATIVE_OFFSET = -100;
    private static final Integer POSITIVE_OFFSET = 100;
    private static final Integer DEFAULT_OFFSET = 0;

    @ParameterizedTest
    @ValueSource(strings =
            {"TOTAL", "PASSED", "FAILED", "SKIPPED", "PRODUCT BUG", "AUTO BUG", "SYSTEM ISSUE", "TO INVESTIGATE"})
    void checkLaunchesTableContainsExpectedColumn(String column) {
        openPageAndLoginAsDefaultUser()
                .openLauchesTab()
                .isLaunchesTableContainsColumn(column);
    }


    @Test
    void checkAddFilterButtonIsNotIntoViewAfterScrollToBottom() {
        openPageAndLoginAsDefaultUser()
                .openLauchesTab()
                .scrollToPageBottom()
                .isAddFilterButtonNotIntoView();
    }

    @Test
    void checkWidgetWidthIsChangedAfterResize() {
        WidgetsPage widgetsPage = openPageAndLoginAsDefaultUser()
                .openDashboardsTab()
                .openDashboard("DEMO DASHBOARD");

        Double previousWidgetWidth = widgetsPage.getWidgetWidth();

        Double widgetWidthAfterResize = widgetsPage
                .resizeWidget(NEGATIVE_OFFSET, DEFAULT_OFFSET)
                .getWidgetWidth();

        assertThat(widgetWidthAfterResize).isLessThan(previousWidgetWidth);

        widgetsPage.resizeWidget(POSITIVE_OFFSET, DEFAULT_OFFSET); // cleanup
    }
}
