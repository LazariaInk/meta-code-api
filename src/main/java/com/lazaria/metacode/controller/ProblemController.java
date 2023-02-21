package com.lazaria.metacode.controller;


import com.lazaria.metacode.model.Problem;
import com.lazaria.metacode.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/admin/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/all")
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable int id) {
        return problemService.getProblemById(id);
    }

    @PostMapping("/create")
    public Problem addProblem(@RequestBody Problem problem) {
        return problemService.addProblem(problem);
    }

    @PutMapping("edit/{id}")
    public Problem updateProblem(@PathVariable int id, @RequestBody Problem updatedProblem) {
        return problemService.updateProblem(id, updatedProblem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProblem(@PathVariable int id) {
        problemService.deleteProblem(id);
    }
}
