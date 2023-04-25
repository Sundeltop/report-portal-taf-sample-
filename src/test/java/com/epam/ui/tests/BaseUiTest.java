package com.epam.ui.tests;

import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.epam.extensions.ui.EnableAllureExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({
        EnableAllureExtension.class,
        ScreenShooterExtension.class,
        BrowserPerTestStrategyExtension.class
})
public class BaseUiTest {
}
