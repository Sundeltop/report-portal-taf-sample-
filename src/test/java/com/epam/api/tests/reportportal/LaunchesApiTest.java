package com.epam.api.tests.reportportal;

import com.epam.api.pojos.GetLaunchResponse;
import com.epam.api.tests.BaseApiTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LaunchesApiTest extends BaseApiTest {

    private static final String TEST_LAUNCH_NAME = "Test Launch";

    // GET

    @Test
    void verifyGetAllLaunches() {
        api.launchClient().createLaunch(TEST_LAUNCH_NAME);

        List<GetLaunchResponse> lauches = api.launchClient().getLaunches();

        assertThat(lauches)
                .isNotEmpty()
                .extracting(GetLaunchResponse::getName).contains(TEST_LAUNCH_NAME);
    }

    @Test
    void verifyGetNonExistingLaunchById() {
        Integer id = api.launchClient().prepareNonExistingLaunch();

        api.launchClient().getLaunchById(id, SC_NOT_FOUND);
    }

    // POST

    @Test
    void verifyAnalyzeLaunch() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();

        api.launchClient().analyzeLaunch(id, SC_OK);
    }

    @Test
    void verifyAnalyzeNonExistingLaunch() {
        Integer id = api.launchClient().prepareNonExistingLaunch();

        api.launchClient().analyzeLaunch(id, SC_NOT_FOUND);
    }

    // PUT

    @Test
    void verifyUpdateLaunchById() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();
        String updatedLaunchDescription = "Demo launch updated by autotest";

        api.launchClient().updateLaunchById(id, updatedLaunchDescription, SC_OK);

        assertThat(api.launchClient().getLaunchById(id))
                .extracting(GetLaunchResponse::getDescription).isEqualTo(updatedLaunchDescription);
    }

    @Test
    void verifyStopAlreadyStoppedLaunch() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();

        api.launchClient().stopLaunch(id, SC_NOT_ACCEPTABLE);
    }

    @Test
    void verifyUpdateNotExistingLaunch() {
        Integer id = api.launchClient().prepareNonExistingLaunch();
        String updatedLaunchDescription = "Demo launch updated by autotest";

        api.launchClient().updateLaunchById(id, updatedLaunchDescription, SC_NOT_FOUND);
    }

    // DELETE

    @Test
    void verifyDeleteLaunchById() {
        Integer id = api.launchClient().createLaunch(TEST_LAUNCH_NAME).getId();

        api.launchClient().deleteLaunchById(id, SC_OK);
        api.launchClient().getLaunchById(id, SC_NOT_FOUND);
    }

    @Test
    void verifyDeleteNonExistingLaunchById() {
        Integer id = api.launchClient().prepareNonExistingLaunch();

        api.launchClient().deleteLaunchById(id, SC_NOT_FOUND);
    }

    @AfterEach
    void clearTestData() {
        api.launchClient().clearTestData();
    }
}
