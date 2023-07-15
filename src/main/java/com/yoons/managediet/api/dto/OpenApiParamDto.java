package com.yoons.managediet.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OpenApiParamDto {
    
    @JsonProperty("RCP_NM")
    private String recipeName;              //메뉴명(필수)

    @JsonProperty("RCP_PARTS_DTLS")
    private String partDetail;             //재료정보1

    @JsonProperty("CHNG_DT")
    private String changeDate;             //변경일자(YYYYMMDD)

    @JsonProperty("RCP_PAT")
    private String partType;               //요리종류 ex(반찬, 국, 후식 등)
}
