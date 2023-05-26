package com.epam.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static com.epam.config.ConfigurationManager.configuration;
import static java.net.URI.create;
import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.time.Duration.ofSeconds;

public abstract class BaseRestClient {

    protected final HttpClient client;
    protected final ObjectMapper mapper;
    private final HttpRequest.Builder request;
    private final String baseUri;

    public BaseRestClient(String accessToken) {
        baseUri = String.format("%s/api/v1/default_personal%s", configuration().baseUrl(), getPathToResource());

        request = HttpRequest.newBuilder()
                .timeout(ofSeconds(10))
                .version(HTTP_1_1)
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", accessToken));

        client = newHttpClient();

        mapper = new ObjectMapper();
    }

    public abstract String getPathToResource();

    protected HttpRequest GET(String path) {
        return request
                .GET()
                .uri(appendUri(path))
                .build();
    }

    protected HttpRequest GET() {
        return GET(null);
    }

    protected HttpRequest POST(String jsonBody, String path) {
        return request
                .POST(ofString(jsonBody))
                .uri(appendUri(path))
                .build();
    }

    protected HttpRequest POST(String jsonBody) {
        return POST(jsonBody, null);
    }

    protected HttpRequest PUT(String jsonBody, String path) {
        return request
                .PUT(ofString(jsonBody))
                .uri(appendUri(path))
                .build();
    }

    protected HttpRequest DELETE(String path) {
        return request
                .DELETE()
                .uri(appendUri(path))
                .build();
    }

    private URI appendUri(String path) {
        if (path == null) {
            return create(baseUri);
        }
        return create(String.format("%s%s", baseUri, path));
    }
}
