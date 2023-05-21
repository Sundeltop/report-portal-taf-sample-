package com.epam.api.client;

import com.epam.api.pojos.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.time.Instant.now;
import static java.util.Collections.singletonList;
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

    public CreateLaunchResponse createLaunch(String name) {
        createdLaunch = given().spec(requestSpecification)
                .body(createLaunchRequest(name))
                .post()
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .as(CreateLaunchResponse.class);

        Integer launchId = getLaunchIdByUUID(createdLaunch.getUuid());
        stopLaunch(launchId); // cancel processing
        createdLaunch.setId(launchId);

        return createdLaunch;
    }

    public void analyzeLaunch(Integer id, Integer expectedStatusCode) {
        AnalyzeLaunchRequest analyzeLaunchRequest = AnalyzeLaunchRequest.builder()
                .analyzeItemsMode(singletonList("TO_INVESTIGATE"))
                .analyzerMode("ALL")
                .analyzerTypeName("autoAnalyzer")
                .launchId(id)
                .build();

        given().spec(requestSpecification)
                .body(analyzeLaunchRequest)
                .post("/analyze")
                .then()
                .statusCode(expectedStatusCode);
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

    public GetLaunchResponse getLaunchById(Integer id, Integer expectedStatusCode) {
        return given().spec(requestSpecification)
                .get("/{id}", id)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .as(GetLaunchResponse.class);
    }

    public GetLaunchResponse getLaunchById(Integer id) {
        return getLaunchById(id, SC_OK);
    }

    public void deleteLaunchById(Integer id, Integer expectedStatusCode) {
        given().spec(requestSpecification)
                .delete("/{id}", id)
                .then()
                .statusCode(expectedStatusCode);

        createdLaunch = null;
    }

    public void deleteLaunchById(Integer id) {
        deleteLaunchById(id, SC_OK);
    }

    public void stopLaunch(Integer id, Integer expectedStatusCode) {
        StopLaunchRequest stopLaunchRequest = StopLaunchRequest.builder()
                .endTime(now().toString())
                .build();

        given().spec(requestSpecification)
                .body(stopLaunchRequest)
                .put("/{id}/stop", id)
                .then()
                .statusCode(expectedStatusCode);
    }

    public void stopLaunch(Integer id) {
        stopLaunch(id, SC_OK);
    }

    public void updateLaunchById(Integer id, String description, Integer expectedStatusCode) {
        CreateLaunchRequest updateLaunchRequest = CreateLaunchRequest.builder()
                .description(description)
                .build();

        given().spec(requestSpecification)
                .body(updateLaunchRequest)
                .put("/{id}/update", id)
                .then()
                .statusCode(expectedStatusCode);
    }

    public Integer prepareNonExistingLaunch() {
        Integer id = createLaunch("Test Launch").getId();

        deleteLaunchById(id);

        return id;
    }

    public void clearTestData() {
        if (nonNull(createdLaunch)) {
            deleteLaunchById(createdLaunch.getId());
        }
    }

    private Integer getLaunchIdByUUID(String uuid) {
        return given().spec(requestSpecification)
                .get("/uuid/{uuid}", uuid)
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("id");
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
