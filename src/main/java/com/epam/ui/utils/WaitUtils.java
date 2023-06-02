package com.epam.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static com.epam.config.ConfigurationManager.configuration;
import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;
import static java.time.Duration.ofSeconds;

public class WaitUtils {

    private WaitUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static <T> T waitFor(Function<? super WebDriver, T> condition) {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(configuration().explicitTimeout()));
        wait.until(condition);

        return condition.apply(driver);
    }

    public static WebElement waitForTextToBePresentInElement(WebElement element) {
        waitFor((ExpectedCondition<Boolean>) d -> element.getText().length() != 0);

        return element;
    }
}
