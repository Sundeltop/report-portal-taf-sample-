package com.epam.ui.tests;

import com.epam.ui.pages.reportportal.HomePage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class ReportPortalTest {

    @Test
    void checkCanOpenReportPortal() {
        page(HomePage.class)
                .isHeaderDisplayedWithText("AI-powered Test Automation Dashboard");
    }
}
