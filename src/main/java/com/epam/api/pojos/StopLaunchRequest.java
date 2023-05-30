package com.epam.api.pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StopLaunchRequest {

    private String endTime;
}
