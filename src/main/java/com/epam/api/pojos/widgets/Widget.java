package com.epam.api.pojos.widgets;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Widget {

    private String widgetId;
    private String widgetType;
    private WidgetPosition widgetPosition;
    private WidgetSize widgetSize;
}
