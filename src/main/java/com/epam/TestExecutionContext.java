package com.epam;

import com.epam.api.pojos.CreateLaunchResponse;

public class TestExecutionContext {
    private static final ThreadLocal<CreateLaunchResponse> launchContainer = new ThreadLocal<>();

    public static void setLaunch(CreateLaunchResponse launch) {
        launchContainer.set(launch);
    }

    public static CreateLaunchResponse getLaunch() {
        return launchContainer.get();
    }
}
