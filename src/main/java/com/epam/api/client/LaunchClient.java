package com.epam.api.client;

import com.epam.api.pojos.CreateLaunchRequest;
import com.epam.api.pojos.CreateLaunchResponse;
import com.epam.api.pojos.GetLaunchResponse;
import com.epam.api.pojos.StopLaunchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.time.Instant.now;
import static java.util.Objects.nonNull;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class LaunchClient extends BaseRestClient {

    private CreateLaunchResponse createdLaunch;

    public LaunchClient(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getPathToResource() {
        return "/launch";
    }

    @SneakyThrows
    public CreateLaunchResponse createLaunch(String name) {
        String jsonBody = mapper.writeValueAsString(createLaunchRequest(name));

        HttpResponse<String> response = client.send(POST(jsonBody), ofString());

        assertThat(response.statusCode()).isEqualTo(SC_CREATED);

        createdLaunch = mapper.readValue(response.body(), CreateLaunchResponse.class);

        Integer launchId = getLaunchIdByUUID(createdLaunch.getUuid());
        stopLaunch(launchId); // cancel processing
        createdLaunch.setId(launchId);

        return createdLaunch;
    }

    @SneakyThrows
    public List<GetLaunchResponse> getLaunches() {
        HttpResponse<String> response = client.send(GET(), ofString());

        assertThat(response.statusCode()).isEqualTo(SC_OK);

        return mapper.readValue(
                mapper.readTree(response.body()).path("content").toString(), new TypeReference<>() {
                }
        );
    }

    @SneakyThrows
    public void stopLaunch(Integer id) {
        StopLaunchRequest stopLaunchRequest = StopLaunchRequest.builder()
                .endTime(now().toString())
                .build();

        String jsonBody = mapper.writeValueAsString(stopLaunchRequest);

        HttpResponse<String> response = client.send(PUT(jsonBody, String.format("/%d/stop", id)), ofString());

        assertThat(response.statusCode()).isEqualTo(SC_OK);
    }

    @SneakyThrows
    public void deleteLaunchById(Integer id) {
        HttpResponse<String> response = client.send(DELETE(String.format("/%d", id)), ofString());

        assertThat(response.statusCode()).isEqualTo(SC_OK);
        createdLaunch = null;
    }

    @SneakyThrows
    public void clearTestData() {
        if (nonNull(createdLaunch)) {
            deleteLaunchById(createdLaunch.getId());
        }
    }

    private Integer getLaunchIdByUUID(String uuid) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(GET(String.format("/uuid/%s", uuid)), ofString());

        assertThat(response.statusCode()).isEqualTo(SC_OK);

        return mapper.readTree(response.body()).path("id").asInt();
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
