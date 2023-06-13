package com.epam.extensions.ui;

import com.epam.api.RestWrapper;
import com.epam.ui.annotations.WidgetCleanup;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ResourceCleanupExtension implements AfterEachCallback {

    private final RestWrapper api = new RestWrapper();

    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getRequiredTestMethod().isAnnotationPresent(WidgetCleanup.class)) {
            api.dashboardClient().resetWidgetToDefaults();
        }
    }
}
