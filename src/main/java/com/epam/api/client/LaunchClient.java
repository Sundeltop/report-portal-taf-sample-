package com.epam.api.client;

import com.epam.api.RestResponse;
import com.epam.api.pojos.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.time.Instant.now;
import static java.util.Collections.singletonList;
import static java.util.Objects.nonNull;
import static java.util.UUID.randomUUID;
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
        RestResponse<CreateLaunchResponse> response = new RestResponse<>(
                given().spec(requestSpecification).body(createLaunchRequest(name)).post(),
                r -> r.as(CreateLaunchResponse.class)
        );
        response.validate().statusCode(SC_CREATED);

        createdLaunch = response.extract();

        Integer launchId = getLaunchIdByUUID(createdLaunch.getUuid()).extract();
        stopLaunch(launchId); // cancel processing
        createdLaunch.setId(launchId);

        return createdLaunch;
    }

    public RestResponse<Void> startLaunchAutoAnalyzer(Integer id) {
        AnalyzeLaunchRequest analyzeLaunchRequest = AnalyzeLaunchRequest.builder()
                .analyzeItemsMode(singletonList("TO_INVESTIGATE"))
                .analyzerMode("ALL")
                .analyzerTypeName("autoAnalyzer")
                .launchId(id)
                .build();

        return new RestResponse<>(
                given().spec(requestSpecification).body(analyzeLaunchRequest).post("/analyze"),
                null
        );
    }

    public RestResponse<List<GetLaunchResponse>> getLaunches() {
        return new RestResponse<>(
                given().spec(requestSpecification).get(),
                response -> response.jsonPath().getList("content", GetLaunchResponse.class)
        );
    }

    public RestResponse<GetLaunchResponse> getLaunchById(Integer id) {
        return new RestResponse<>(
                given().spec(requestSpecification).get("/{id}", id),
                response -> response.as(GetLaunchResponse.class)
        );
    }

    public RestResponse<Integer> getLaunchIdByUUID(String uuid) {
        return new RestResponse<>(
                given().spec(requestSpecification).get("/uuid/{uuid}", uuid),
                r -> r.path("id")
        );
    }

    public RestResponse<Void> deleteLaunchById(Integer id) {
        RestResponse<Void> response = new RestResponse<>(
                given().spec(requestSpecification).delete("/{id}", id),
                null
        );

        createdLaunch = null;

        return response;
    }

    public RestResponse<Void> stopLaunch(Integer id) {
        StopLaunchRequest stopLaunchRequest = StopLaunchRequest.builder()
                .endTime(now().toString())
                .build();

        return new RestResponse<>(
                given().spec(requestSpecification).body(stopLaunchRequest).put("/{id}/stop", id),
                null
        );
    }

    public RestResponse<Void> updateLaunchById(Integer id, String description) {
        CreateLaunchRequest updateLaunchRequest = CreateLaunchRequest.builder()
                .description(description)
                .build();

        return new RestResponse<>(
                given().spec(requestSpecification).body(updateLaunchRequest).put("/{id}/update", id),
                null
        );
    }

    public Integer prepareNonExistingLaunch() {
        Integer id = createLaunch("Test Launch %s".formatted(randomUUID())).getId();

        deleteLaunchById(id).validate().statusCode(SC_OK);

        return id;
    }

    public void clearTestData() {
        if (nonNull(createdLaunch)) {
            deleteLaunchById(createdLaunch.getId()).validate().statusCode(SC_OK);
        }
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
