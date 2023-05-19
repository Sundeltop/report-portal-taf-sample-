package com.epam.api.client;

import com.epam.api.pojos.LaunchResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
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
}
