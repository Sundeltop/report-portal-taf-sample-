package com.epam.api.tests.reportportal;

import com.epam.api.pojos.GetLaunchResponse;
import com.epam.api.tests.BaseApiTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LaunchesApiTest extends BaseApiTest {

    private static final String TEST_LAUNCH_NAME = "Test Launch";

    @Test
    void verifyGetAllLaunches() {
        api.launchClient().createLaunch(TEST_LAUNCH_NAME);

        List<GetLaunchResponse> lauches = api.launchClient().getLaunches();

        assertThat(lauches)
                .isNotEmpty()
                .extracting(GetLaunchResponse::getName).contains(TEST_LAUNCH_NAME);
    }

    @AfterEach
    void clearTestData() {
        api.launchClient().clearTestData();
    }
}
