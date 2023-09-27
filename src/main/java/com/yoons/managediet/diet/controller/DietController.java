package com.yoons.managediet.diet.controller;

import com.yoons.managediet.diet.dto.DailyOutputDto;
import com.yoons.managediet.diet.dto.DietOutputDto;
import com.yoons.managediet.diet.dto.DietSaveInputDto;
import com.yoons.managediet.diet.dto.DietSearchInputDto;
import com.yoons.managediet.diet.service.AnalyzeDailyDietService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DietController {
    private final AnalyzeDailyDietService analyzeDailyDietService;

    @Parameter(name = "date", description = "date", required = true)
    @GetMapping("/diet/daily")
    public ResponseEntity<DailyOutputDto> getOneDayDiet(
            @Parameter(hidden = true) @ModelAttribute DietSearchInputDto dietSearchInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.getOneDayDiet(dietSearchInputDto.getDate()));
    }

    @Parameters({
            @Parameter(name = "date", description = "date", required = true),
            @Parameter(name = "time", description = "time", required = true)
    })
    @GetMapping("/diet/daily/time")
    public ResponseEntity<DietOutputDto> getPartDiet(
            @Parameter(hidden = true) @ModelAttribute DietSearchInputDto dietSearchInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.getPartDiet(dietSearchInputDto.getDate(), dietSearchInputDto.getTime()));
    }

    @PostMapping("/daily/save")
    public ResponseEntity<DietOutputDto> dailyDietSave(@RequestBody DietSaveInputDto dietSaveInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.saveDiet(dietSaveInputDto));
    }
}
