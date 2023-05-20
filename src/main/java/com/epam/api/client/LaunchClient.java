package com.epam.api.client;

import com.epam.api.pojos.CreateLaunchRequest;
import com.epam.api.pojos.CreateLaunchResponse;
import com.epam.api.pojos.GetLaunchResponse;
import com.epam.api.pojos.StopLaunchRequest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.time.Instant.now;
import static java.util.Objects.nonNull;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class LaunchClient extends BaseRestClient {

    private CreateLaunchResponse createdLaunch;

    public LaunchClient(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getPathToResource() {
        return "/launch";
    }

    public List<GetLaunchResponse> getLaunches() {
        return given().spec(requestSpecification)
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .jsonPath()
                .getList("content", GetLaunchResponse.class);
    }

    public CreateLaunchResponse createLaunch(String name) {
        createdLaunch = given().spec(requestSpecification)
                .body(createLaunchRequest(name))
                .post()
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .as(CreateLaunchResponse.class);

        return createdLaunch;
    }

    public void deleteLaunch(String uuid) {
        Integer id = getLaunchIdByUUID(uuid);

        stopLaunch(id);

        given().spec(requestSpecification)
                .delete("/{id}", id)
                .then()
                .statusCode(SC_OK);

        createdLaunch = null;
    }

    private Integer getLaunchIdByUUID(String uuid) {
        return given().spec(requestSpecification)
                .get("/uuid/{uuid}", uuid)
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("id");
    }

    public void clearTestData() {
        if (nonNull(createdLaunch)) {
            deleteLaunch(createdLaunch.getUuid());
        }
    }

    private void stopLaunch(Integer id) {
        StopLaunchRequest stopLaunchRequest = StopLaunchRequest.builder()
                .endTime(now().toString())
                .build();

        given().spec(requestSpecification)
                .body(stopLaunchRequest)
                .put("/{id}/stop", id)
                .then()
                .statusCode(SC_OK);
    }

    private CreateLaunchRequest createLaunchRequest(String name) {
        return CreateLaunchRequest.builder()
                .name(name)
                .description("Demo launch created by autotest")
                .rerun(false)
                .startTime(now().toString())
                .build();
    }
}
