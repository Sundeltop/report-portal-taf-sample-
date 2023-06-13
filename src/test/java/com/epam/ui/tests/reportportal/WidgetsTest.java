package com.epam.ui.tests.reportportal;

import com.epam.ui.annotations.WidgetCleanup;
import com.epam.ui.pages.reportportal.WidgetsPage;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WidgetsTest extends BaseUiTest {

    private static final Integer NEGATIVE_OFFSET = -100;
    private static final Integer DEFAULT_OFFSET = 0;

    @Test
    @WidgetCleanup
    void checkWidgetWidthIsChangedAfterResize() {
        WidgetsPage widgetsPage = openPageAndLoginAsDefaultUser()
                .openDashboardsTab()
                .openDashboard("DEMO DASHBOARD");

        Double previousWidgetWidth = widgetsPage.getWidgetWidth();

        Double widgetWidthAfterResize = widgetsPage
                .resizeWidget(NEGATIVE_OFFSET, DEFAULT_OFFSET)
                .getWidgetWidth();

        assertThat(widgetWidthAfterResize).isLessThan(previousWidgetWidth);
    }
}
