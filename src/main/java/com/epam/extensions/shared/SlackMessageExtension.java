package com.epam.extensions.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import static com.epam.config.ConfigurationManager.configuration;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

@Log4j2
public class SlackMessageExtension implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        sendSlackMessage("@Test %s is passed successfully".formatted(context.getRequiredTestMethod().getName()));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        sendSlackMessage("@Test %s is failed: Error stacktrace:\n%s"
                .formatted(context.getRequiredTestMethod().getName(), cause.getMessage()));
    }

    private static void sendSlackMessage(String message) {
        ObjectNode messageBody = new ObjectMapper().createObjectNode()
                .put("text", message);

        int statusCode = given()
                .contentType(JSON)
                .baseUri("https://hooks.slack.com/services")
                .body(messageBody)
                .post("/%s".formatted(configuration().slackToken()))
                .statusCode();

        if (SC_OK != statusCode) {
            log.error("Could not send message to Slack. Please check your token");
        }
    }
}
