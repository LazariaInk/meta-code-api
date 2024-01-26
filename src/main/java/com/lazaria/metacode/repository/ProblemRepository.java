package com.lazaria.metacode.repository;

import com.lazaria.metacode.model.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    Page<Problem> findAll(Pageable pageable);

    Problem findByProblemName(String problemName);
}
