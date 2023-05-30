package com.epam.api.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLaunchRequest {

    private String name;
    private String description;
    private Boolean rerun;
    private String startTime;
}
