package com.epam.api.tests.reportportal;

import com.epam.api.RestResponse;
import com.epam.api.pojos.GetLaunchResponse;
import com.epam.api.tests.BaseApiTest;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LaunchesApiTest extends BaseApiTest {

    private static final String TEST_LAUNCH_NAME = "Test Launch %s".formatted(randomUUID());

    // GET

    @Test
    @Story("MDP-1")
    void verifyGetAllLaunches() {
        api.launchClient().createLaunch(TEST_LAUNCH_NAME);

        RestResponse<List<GetLaunchResponse>> response = api.launchClient().getLaunches();

        response.validate().statusCode(SC_OK);

        assertThat(response.extract())
                .isNotEmpty()
                .extracting(GetLaunchResponse::getName).contains(TEST_LAUNCH_NAME);
    }

    @Test
    void verifyGetNonExistingLaunchById() {
        Integer id = api.launchClient().prepareNonExistingLaunch();

        api.launchClient().getLaunchById(id).validate().statusCode(SC_NOT_FOUND);
    }

    // POST

    @Test
    void verifyStartLaunchAutoAnalyzer() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();

        api.launchClient().startLaunchAutoAnalyzer(id).validate().statusCode(SC_OK);
    }

    @Test
    void verifyStartLaunchAutoAnalyzerOfNonExistingLaunch() {
        Integer id = api.launchClient().prepareNonExistingLaunch();

        api.launchClient().startLaunchAutoAnalyzer(id).validate().statusCode(SC_NOT_FOUND);
    }

    // PUT

    @Test
    void verifyUpdateLaunchById() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();
        String updatedLaunchDescription = "Demo launch updated by autotest";

        api.launchClient().updateLaunchById(id, updatedLaunchDescription).validate().statusCode(SC_OK);

        RestResponse<GetLaunchResponse> response = api.launchClient().getLaunchById(id);

        response.validate().statusCode(SC_OK);

        assertThat(response.extract())
                .extracting(GetLaunchResponse::getDescription).isEqualTo(updatedLaunchDescription);
    }

    @Test
    void verifyStopAlreadyStoppedLaunch() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();

        api.launchClient().stopLaunch(id).validate().statusCode(SC_NOT_ACCEPTABLE);
    }

    @Test
    void verifyUpdateNotExistingLaunch() {
        Integer id = api.launchClient().prepareNonExistingLaunch();
        String updatedLaunchDescription = "Demo launch updated by autotest";

        api.launchClient().updateLaunchById(id, updatedLaunchDescription).validate().statusCode(SC_NOT_FOUND);
    }

    // DELETE

    @Test
    void verifyDeleteLaunchById() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();
        api.launchClient().getLaunchById(id).validate().statusCode(SC_OK);

        api.launchClient().deleteLaunchById(id).validate().statusCode(SC_OK);

        api.launchClient().getLaunchById(id).validate().statusCode(SC_NOT_FOUND);
    }

    @Test
    void verifyDeleteNonExistingLaunchById() {
        Integer id = api.launchClient().prepareNonExistingLaunch();

        api.launchClient().deleteLaunchById(id).validate().statusCode(SC_NOT_FOUND);
    }

    @AfterEach
    void clearTestData() {
        api.launchClient().clearTestData();
    }
}
