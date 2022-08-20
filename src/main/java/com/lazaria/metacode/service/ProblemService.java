package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Problem;
import com.lazaria.metacode.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;


    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public List<Problem> findAllProblems() {
        return problemRepository.findAll();
    }

    public void deleteProblemById(int problemId) {
        problemRepository.deleteById(problemId);
    }

    public Optional<Problem> editProblem(Problem problemForEdit, int problemId) {
        return problemRepository.findById(problemId)
                .map(problem -> {
                    problem.setProblemComplexity(problemForEdit.getProblemComplexity());
                    problem.setProblemContent(problemForEdit.getProblemContent());
                    problem.setProblemImage(problemForEdit.getProblemImage());
                    problem.setProblemName(problemForEdit.getProblemName());
                    problem.setProblemTheme(problemForEdit.getProblemTheme());
                    return problemRepository.save(problem);
                });
    }
}
