package com.epam.api;

import com.epam.api.client.LaunchClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static com.epam.config.ConfigurationManager.configuration;
import static java.net.URI.create;
import static java.net.URLEncoder.encode;
import static java.net.http.HttpClient.Version.HTTP_1_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.Duration.ofSeconds;

public class RestWrapper {

    private final LaunchClient launchClient;

    public RestWrapper() {
        String accessToken = getAccessToken();
        launchClient = new LaunchClient(accessToken);
    }

    @SneakyThrows
    private String getAccessToken() {
        HttpRequest request = HttpRequest.newBuilder()
                .timeout(ofSeconds(10))
                .version(HTTP_1_1)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(create(String.format("%s/uat/sso/oauth/token", configuration().baseUrl())))
                .POST(getFormData())
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .authenticator(getBasicAuth("ui", "uiman"))
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readTree(response.body()).path("access_token").asText();
    }

    private static HttpRequest.BodyPublisher getFormData() {
        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", "password");
        formData.put("username", configuration().defaultUserLogin());
        formData.put("password", configuration().defaultUserPassword());

        StringBuilder stringBuilder = new StringBuilder();

        for (Entry<String, String> entry : formData.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(encode(entry.getKey(), UTF_8));
            stringBuilder.append("=");
            stringBuilder.append(encode(entry.getValue(), UTF_8));
        }

        return HttpRequest.BodyPublishers.ofString(stringBuilder.toString());
    }

    private static Authenticator getBasicAuth(String username, String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password.toCharArray());
            }
        };
    }

    public LaunchClient launchClient() {
        return launchClient;
    }
}
