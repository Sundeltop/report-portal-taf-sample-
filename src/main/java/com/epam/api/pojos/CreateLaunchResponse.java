package com.epam.api.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLaunchResponse {
    private String id;
    private Integer number;
}
