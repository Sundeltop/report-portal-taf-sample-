package com.epam.api.pojos.widgets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class WidgetSize {

    private Integer width;
    private Integer height;
}
