package com.epam.api.pojos.widgets;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateWidgetsRequest {

    private String name;
    private List<Widget> updateWidgets;
}