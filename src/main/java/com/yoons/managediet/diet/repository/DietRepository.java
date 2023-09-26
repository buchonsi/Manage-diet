package com.yoons.managediet.diet.repository;

import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {

    @Modifying
    @Query(value = "delete from diet where applied_date = ?1 and time = ?2", nativeQuery = true)
    void deleteAllByDailyAndDate(String appliedDate, String typeOfDate);

    List<Diet> findByDietAppliedDate(LocalDate appliedDate);
    List<Diet> findByDietAppliedDateAndTypeOfTime(LocalDate appliedDate, TypeOfTime typeOfTime);
}
