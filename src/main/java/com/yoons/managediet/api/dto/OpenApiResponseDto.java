package com.yoons.managediet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenApiResponseDto {

    @JsonProperty("row")
    private List<RawDto> rawDto;
}
