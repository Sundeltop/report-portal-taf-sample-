package com.epam.ui.pages.reportportal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.config.ConfigurationManager.configuration;
import static com.epam.ui.WebDriverStorage.getWebDriver;
import static com.epam.ui.utils.WaitUtils.waitFor;
import static com.epam.ui.utils.WaitUtils.waitForTextToBePresentInElement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage {

    @FindBy(css = ".loginForm__login-field--2NeYx input")
    private WebElement loginInput;

    @FindBy(css = ".loginForm__password-field--2IH1A input")
    private WebElement passwordInput;

    @FindBy(css = ".loginForm__login-button-container--1mHGW button")
    private WebElement confirmLoginButton;

    @FindBy(css = ".notificationItem__message-container--16jY2")
    private WebElement notification;

    @FindBy(css = ".loginForm__forgot-pass--2mB6-")
    private WebElement forgotPasswordButton;

    @FindBy(css = ".blockHeader__huge-message--_I_zH")
    private WebElement forgotPasswordHeader;

    @FindBy(xpath = "//span[text()='Launches']/ancestor::div[@class='sidebarButton__sidebar-nav-btn--1prEO']")
    private WebElement launchesTab;

    @FindBy(xpath = "//span[text()='Dashboards']/ancestor::div[@class='sidebarButton__sidebar-nav-btn--1prEO']")
    private WebElement dashboardsTab;

    public LoginPage() {
        initElements(getWebDriver(), this);
    }

    public LoginPage loginAsDefaultUser() {
        return login(configuration().defaultUserLogin(), configuration().defaultUserPassword());
    }

    public LaunchesPage openLauchesTab() {
        waitFor(elementToBeClickable(launchesTab)).click();

        return new LaunchesPage();
    }

    public DashboardsPage openDashboardsTab() {
        waitFor(elementToBeClickable(dashboardsTab)).click();

        return new DashboardsPage();
    }

    public LoginPage login(String login, String password) {
        waitFor(visibilityOf(loginInput)).sendKeys(login);
        passwordInput.sendKeys(password);
        confirmLoginButton.click();

        return this;
    }

    public void isLoginNotificationDisplayedWithText(String expectedNotificationText) {
        assertThat(waitForTextToBePresentInElement(notification).getText()).isEqualTo(expectedNotificationText);
    }

    public LoginPage forgotPassword() {
        waitFor(elementToBeClickable(forgotPasswordButton)).click();

        return this;
    }

    public void isRestoreOptionAvailable() {
        assertThat(forgotPasswordHeader.getText()).isEqualTo("Forgot password?");
    }
}
