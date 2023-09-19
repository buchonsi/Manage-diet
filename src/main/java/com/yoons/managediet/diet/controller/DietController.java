package com.yoons.managediet.diet.controller;

import com.yoons.managediet.diet.dto.DietInputDto;
import com.yoons.managediet.diet.dto.DietOutputDto;
import com.yoons.managediet.diet.service.AnalyzeDailyDietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DietController {
    private final AnalyzeDailyDietService analyzeDailyDietService;

//    @GetMapping("/diet/daily")
//    public ResponseEntity<DailyOutputDto> getOneDayDiet(@RequestAttribute LocalDate appliedDate) {
//        return ResponseEntity.ok(analyzeDailyDietService.getOneDayDiet(appliedDate));
//    }

    @PostMapping("/daily/save")
    public ResponseEntity<DietOutputDto> dailyDietSave(@RequestBody DietInputDto dietInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.saveDiet(dietInputDto));
    }
}
