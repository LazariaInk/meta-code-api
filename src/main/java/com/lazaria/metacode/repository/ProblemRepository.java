package com.lazaria.metacode.repository;

import com.lazaria.metacode.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
}
