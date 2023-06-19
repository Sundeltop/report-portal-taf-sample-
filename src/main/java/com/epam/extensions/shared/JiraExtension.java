package com.epam.extensions.shared;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Story;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import static com.epam.config.ConfigurationManager.configuration;
import static com.epam.extensions.shared.JiraExtension.TicketStatus.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

@Log4j2
public class JiraExtension implements BeforeEachCallback, TestWatcher {

    @Override
    public void beforeEach(ExtensionContext context) {
        changeJiraTestStatus(context, RUNNING);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        changeJiraTestStatus(context, PENDING);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        changeJiraTestStatus(context, PASSED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        changeJiraTestStatus(context, FAILED);
    }

    private static void changeJiraTestStatus(ExtensionContext context, TicketStatus statusTo) {
        Story story = context.getRequiredTestMethod().getAnnotation(Story.class);

        if (story != null) {
            performStatusTransition(story.value(), statusTo);
        }
    }

    private static void performStatusTransition(String testCaseId, TicketStatus status) {
        JsonNode transitionBody = getTransition(status);

        int statusCode = given()
                .contentType(JSON)
                .baseUri("%s/rest/api/2/issue".formatted(configuration().jiraHost()))
                .auth()
                .preemptive()
                .basic(configuration().jiraUsername(), configuration().jiraPassword())
                .body(transitionBody)
                .post("/{id}/transitions", testCaseId)
                .statusCode();

        if (SC_NO_CONTENT != statusCode) {
            log.error("Could not change Jira ticket status. Please check your configuration");
        }
    }

    private static JsonNode getTransition(TicketStatus status) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode id = mapper.createObjectNode()
                .put("id", status.getCode());

        return mapper.createObjectNode()
                .set("transition", id);
    }

    @Getter
    @AllArgsConstructor
    public enum TicketStatus {
        PENDING(11),
        RUNNING(21),
        PASSED(31),
        FAILED(41);

        private final Integer code;
    }
}
