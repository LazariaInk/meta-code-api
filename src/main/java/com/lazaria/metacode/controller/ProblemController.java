package com.lazaria.metacode.controller;


import com.lazaria.metacode.model.Problem;
import com.lazaria.metacode.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/all")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public Problem getProblemById(@PathVariable int id) {
        return problemService.getProblemById(id);
    }

    @GetMapping("/{problemName}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public Problem getProblemByName(@PathVariable String problemName) {return problemService.findByProblemName(problemName);}

    @PostMapping("/create")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public Problem addProblem(@RequestBody Problem problem) {
        return problemService.addProblem(problem);
    }

    @PutMapping("edit/{id}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public Problem updateProblem(@PathVariable int id, @RequestBody Problem updatedProblem) {
        return problemService.updateProblem(id, updatedProblem);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public void deleteProblem(@PathVariable int id) {
        problemService.deleteProblem(id);
    }
}
