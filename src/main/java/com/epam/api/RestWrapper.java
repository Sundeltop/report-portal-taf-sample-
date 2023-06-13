package com.epam.api;

import com.epam.api.client.DashboardClient;
import com.epam.api.client.LaunchClient;

import static com.epam.config.ConfigurationManager.configuration;
import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class RestWrapper {

    private final LaunchClient launchClient;
    private final DashboardClient dashboardClient;

    public RestWrapper() {
        filters(new RestAssuredLoggingFilter());
        String accessToken = getAccessToken();
        launchClient = new LaunchClient(accessToken);
        dashboardClient = new DashboardClient(accessToken);
    }

    private String getAccessToken() {
        return given()
                .formParam("grant_type", "password")
                .formParam("username", configuration().defaultUserLogin())
                .formParam("password", configuration().defaultUserPassword())
                .auth()
                .basic("ui", "uiman")
                .post(String.format("%s/uat/sso/oauth/token", configuration().baseUrl()))
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("access_token");
    }

    public LaunchClient launchClient() {
        return launchClient;
    }

    public DashboardClient dashboardClient() {
        return dashboardClient;
    }
}
