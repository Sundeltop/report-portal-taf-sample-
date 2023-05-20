package com.epam.api.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLaunchResponse {

    private String owner;
    private Boolean share;
    private String description;
    private Integer id;
    private String uuid;
    private String name;
    private Integer number;
    private Long startTime;
    private Long endTime;
    private Long lastModified;
    private String status;
    private String mode;
    private Double approximateDuration;
    private Boolean hasRetries;
    private Boolean rerun;
}
