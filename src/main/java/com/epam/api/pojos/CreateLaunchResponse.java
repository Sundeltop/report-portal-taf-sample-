package com.epam.api.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class CreateLaunchResponse {

    @JsonProperty("id")
    private String uuid;
    private Integer number;
}
