package com.epam.api.client;

import com.epam.api.pojos.widgets.UpdateWidgetsRequest;
import com.epam.api.pojos.widgets.Widget;
import com.epam.api.pojos.widgets.WidgetPosition;
import com.epam.api.pojos.widgets.WidgetSize;

import static io.restassured.RestAssured.given;
import static java.util.Collections.singletonList;
import static org.apache.http.HttpStatus.SC_OK;

public class DashboardClient extends BaseRestClient {

    public DashboardClient(String accessToken) {
        super(accessToken);
    }

    @Override
    public String getPathToResource() {
        return "/dashboard";
    }

    public void resetWidgetToDefaults() {
        Widget defaultWidget = Widget.builder()
                .widgetId("2")
                .widgetType("statisticTrend")
                .widgetSize(new WidgetSize(7, 6))
                .widgetPosition(new WidgetPosition(0, 0))
                .build();

        UpdateWidgetsRequest updateWidgetsRequest = UpdateWidgetsRequest.builder()
                .name("DEMO DASHBOARD")
                .updateWidgets(singletonList(defaultWidget))
                .build();

        given().spec(requestSpecification)
                .body(updateWidgetsRequest)
                .put("/{id}", 14)
                .then()
                .statusCode(SC_OK);
    }
}
