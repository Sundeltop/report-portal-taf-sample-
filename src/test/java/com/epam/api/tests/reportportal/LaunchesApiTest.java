package com.epam.api.tests.reportportal;

import com.epam.api.pojos.LaunchResponse;
import com.epam.api.tests.BaseApiTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LaunchesApiTest extends BaseApiTest {

    @Test
    void verifyLaunches() {
        List<LaunchResponse> lauches = api.launchClient().getLaunches();
        assertThat(lauches).isNotEmpty();
        assertThat(lauches).extracting(LaunchResponse::getName).contains("Demo Api Tests");
    }
}
