package com.yoons.managediet.diet.service;

import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.diet.repository.DietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietRepositoryService {
    private final DietRepository dietRepository;

    public List<Diet> saveAll(List<Diet> dietList) {
        return dietRepository.saveAll(dietList);
    }

    public void deleteAllByDailyAndDate(String appliedDate, String typeOfDate) {
        dietRepository.deleteAllByDailyAndDate(appliedDate, typeOfDate);
    }

    public List<Diet> findByDietAppliedDate(LocalDate appliedDate) {
        return dietRepository.findByDietAppliedDate(appliedDate);
    }

    public List<Diet> findByDietAppliedDateAndTypeOfTime(LocalDate appliedDate, TypeOfTime typeOfTime) {
        return dietRepository.findByDietAppliedDateAndTypeOfTime(appliedDate, typeOfTime);
    }
}
