package com.epam.stepdefinitions;

import com.epam.api.client.LaunchesClient;
import com.epam.api.pojos.CreateLaunchResponse;
import io.cucumber.java.en.Given;
import org.mockito.Mockito;

import static com.epam.TestExecutionContext.setLaunch;
import static org.mockito.Mockito.when;

public class ApiSteps {

    private LaunchesClient launchesService;

    @Given("Launch {string} is present on the launches tab")
    public void launchIsPresent(String launchName) {
        launchesService = Mockito.mock(LaunchesClient.class);
        when(launchesService.createLaunch())
                .thenReturn(new CreateLaunchResponse(launchName, 5));
        CreateLaunchResponse createLaunchResponse = launchesService.createLaunch();
        setLaunch(createLaunchResponse);
    }
}
