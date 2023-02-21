package com.lazaria.metacode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lazaria.metacode.model.Problem;
import com.lazaria.metacode.repository.ProblemRepository;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblemById(int problemId) {
        return problemRepository.findById(problemId).orElse(null);
    }

    public Problem addProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public Problem updateProblem(int id, Problem updatedProblem) {
        Optional<Problem> foundProblem = problemRepository.findById(id);
        if (foundProblem.isPresent()) {
            Problem problem = foundProblem.get();
            problem.setProblemName(updatedProblem.getProblemName());
            problem.setProblemContent(updatedProblem.getProblemContent());
            problem.setProblemSolution(updatedProblem.getProblemSolution());
            problem.setProblemComplexity(updatedProblem.getProblemComplexity());
            problem.setProblemTheme(updatedProblem.getProblemTheme());
            return problemRepository.save(problem);
        }
        return null;
    }


    public void deleteProblem(int problemId) {
        problemRepository.deleteById(problemId);
    }

}
