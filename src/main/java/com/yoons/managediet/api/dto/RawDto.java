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
    @JsonProperty("INFO_ENG")       //칼로리
    private double calorie;
    @JsonProperty("INFO_CAR")       //탄수화물
    private double carbohydrate;
    @JsonProperty("INFO_PRO")       //단백질
    private double protein;
    @JsonProperty("INFO_FAT")       //지방
    private double fat;
    @JsonProperty("INFO_NA")       //나트륨
    private double sodium;
    @JsonProperty("ATT_FILE_NO_MK")       //이미지(대)
    private String image;
}
