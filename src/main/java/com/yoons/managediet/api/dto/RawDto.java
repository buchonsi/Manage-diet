package com.yoons.managediet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RawDto {

//    @Todo 가져올 데이터 추가 해야함
    @JsonProperty("RCP_NM")
    private String recipeName;
}
