package com.yoons.managediet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RawDto {

    @JsonProperty("RCP_NM")
    private String recipeName;
}
