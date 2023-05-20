package com.epam.api.client;

import com.epam.api.pojos.CreateLaunchRequest;
import com.epam.api.pojos.LaunchResponse;
import com.epam.api.pojos.StopLaunchRequest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.time.Instant.now;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class LaunchClient extends BaseRestClient {

    public LaunchClient(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getPathToResource() {
        return "/launch";
    }

    public List<LaunchResponse> getLaunches() {
        return given().spec(requestSpecification)
                .get()
                .then()
                .statusCode(SC_OK)
                .extract()
                .jsonPath()
                .getList("content", LaunchResponse.class);
    }

    public String createLaunch(String name) {
        return given().spec(requestSpecification)
                .body(createLaunchRequest(name))
                .post()
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .path("id");
    }


    public void deleteLaunch(String uuid) {
        Integer id = getLaunchIdByUUID(uuid);

        stopLaunch(id);

        given().spec(requestSpecification)
                .delete("/{id}", id)
                .then()
                .statusCode(SC_OK);
    }

    private Integer getLaunchIdByUUID(String uuid) {
        return given().spec(requestSpecification)
                .get("/uuid/{uuid}", uuid)
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("id");
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
