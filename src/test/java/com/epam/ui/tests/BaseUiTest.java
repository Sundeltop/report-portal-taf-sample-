package com.epam.ui.tests;

import com.codeborne.selenide.testng.ScreenShooter;
import com.epam.listeners.shared.LoggingListener;
import com.epam.listeners.ui.EnableAllureListener;
import org.testng.annotations.Listeners;

@Listeners({
        EnableAllureListener.class,
        ScreenShooter.class,
        LoggingListener.class
})
public class BaseUiTest {
}
