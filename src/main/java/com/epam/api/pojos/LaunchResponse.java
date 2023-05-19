package com.epam.api.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaunchResponse {

    private final String owner;
    private final Boolean share;
    private final String description;
    private final Integer id;
    private final String uuid;
    private final String name;
    private final Integer number;
    private final Long startTime;
    private final Long endTime;
    private final Long lastModified;
    private final String status;
    private final String mode;
    private final Double approximateDuration;
    private final Boolean hasRetries;
    private final Boolean rerun;
}
