package com.epam.ui.tests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.epam.extensions.ui.EnableAllureExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({
        EnableAllureExtension.class,
        ScreenShooterExtension.class
})
public class BaseUiTest {
}
