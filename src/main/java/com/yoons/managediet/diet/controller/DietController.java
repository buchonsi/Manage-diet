package com.yoons.managediet.diet.controller;

import com.yoons.managediet.diet.dto.DailyOutputDto;
import com.yoons.managediet.diet.dto.DietSaveInputDto;
import com.yoons.managediet.diet.dto.DietOutputDto;
import com.yoons.managediet.diet.dto.DietSearchInputDto;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.diet.service.AnalyzeDailyDietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DietController {
    private final AnalyzeDailyDietService analyzeDailyDietService;

    @GetMapping("/diet/daily")
    public ResponseEntity<DailyOutputDto> getOneDayDiet(@ModelAttribute DietSearchInputDto dietSearchInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.getOneDayDiet(dietSearchInputDto.getDate()));
    }

    @GetMapping("/diet/daily/time")
    public ResponseEntity<DietOutputDto> getPartDiet(@ModelAttribute DietSearchInputDto dietSearchInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.getPartDiet(dietSearchInputDto.getDate(), dietSearchInputDto.getTime()));
    }

    @PostMapping("/daily/save")
    public ResponseEntity<DietOutputDto> dailyDietSave(@RequestBody DietSaveInputDto dietSaveInputDto) {
        return ResponseEntity.ok(analyzeDailyDietService.saveDiet(dietSaveInputDto));
    }
}
