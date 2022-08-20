package com.lazaria.metacode.controller;


import com.lazaria.metacode.dto.Problem;
import com.lazaria.metacode.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @PostMapping("/create")
    public Problem createProblem(@RequestBody Problem problem) {
        return problemService.createProblem(problem);
    }

    @GetMapping("/all")
    public List<Problem> findAllProblems() {
        return problemService.findAllProblems();
    }

    @DeleteMapping("/delete/{sponsorId}")
    public void deleteProblemById(@PathVariable int problemId) {
        problemService.deleteProblemById(problemId);
    }

    @PutMapping("edit/{problemId}")
    public Optional<Problem> editProblem(@RequestBody Problem problemForEdit, @PathVariable int problemId) {
        return problemService.editProblem(problemForEdit, problemId);
    }
}
