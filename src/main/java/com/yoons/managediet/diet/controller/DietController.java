package com.yoons.managediet.diet.controller;

import com.yoons.managediet.diet.dto.DailyInputDto;
import com.yoons.managediet.diet.dto.DailyOutputDto;
import com.yoons.managediet.diet.service.AnalyzeDailyDietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DietController {
    private final AnalyzeDailyDietService analyzeDailyDietService;

    @PostMapping("/daily/save")
    public ResponseEntity<DailyOutputDto> dailyDietSave(DailyInputDto dailyInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.save(dailyInputDto));
    }
}
