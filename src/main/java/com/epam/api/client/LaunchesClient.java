package com.epam.api.client;

import com.epam.api.pojos.CreateLaunchResponse;

public class LaunchesClient extends BaseRestClient {

    @Override
    protected String getPathToResource() {
        return "/api/v1/project/default_personal/launch";
    }

    public CreateLaunchResponse createLaunch() {
        throw new RuntimeException("Service is not implemented");
    }
}
