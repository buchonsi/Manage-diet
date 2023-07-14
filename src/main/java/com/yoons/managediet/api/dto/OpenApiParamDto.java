package com.yoons.managediet.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OpenApiParamDto {

    private String rct_nm;              //메뉴명(필수)
    private String rcp_parts_dtls;      //재료정보1
    private String chng_dt;             //변경일자(YYYYMMDD)
    private String rcp_pat2;            //요리종류 ex(반찬, 국, 후식 등)
}
