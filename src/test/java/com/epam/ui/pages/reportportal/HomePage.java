package com.epam.ui.pages.reportportal;

import com.epam.ui.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {

    public void isHeaderDisplayedWithText(String expectedText) {
        $(".index-page_welcome .section-container")
                .shouldBe(visible)
                .shouldNotBe(empty)
                .shouldHave(text(expectedText));
    }

    @Override
    protected String getURL() {
        return "https://reportportal.io/";
    }
}
