package com.yoons.managediet.diet.repository;

import com.yoons.managediet.diet.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {

}
