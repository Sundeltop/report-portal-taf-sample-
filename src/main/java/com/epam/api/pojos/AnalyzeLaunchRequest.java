package com.epam.api.pojos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnalyzeLaunchRequest {

    private List<String> analyzeItemsMode;
    private String analyzerMode;
    private String analyzerTypeName;
    private Integer launchId;
}
