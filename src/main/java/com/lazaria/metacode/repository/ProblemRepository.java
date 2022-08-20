package com.lazaria.metacode.repository;

import com.lazaria.metacode.dto.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
}
