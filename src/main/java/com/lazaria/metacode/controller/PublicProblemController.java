package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Problem;
import com.lazaria.metacode.model.dao.ProblemInfoDTO;
import com.lazaria.metacode.repository.ProblemRepository;
import com.lazaria.metacode.utils.ProblemMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/problems")
@CrossOrigin(origins = "http://localhost:3000")
public class PublicProblemController {
    @Autowired
    private ProblemRepository problemRepository;

    @GetMapping("/{problemId}/content")
    public ResponseEntity<Problem> getProblem(@PathVariable int problemId) {
        Optional<Problem> problem = problemRepository.findById(problemId);
        return problem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProblemInfoDTO>> getProblemList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String problemNameFilter,
            @RequestParam(required = false) String problemComplexityFilter,
            @RequestParam(required = false) String problemThemeFilter
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Problem> problemsPage = problemRepository.findAll(pageable);

        List<ProblemInfoDTO> problemInfoList = problemsPage.getContent().stream()
                .map(ProblemMapperUtil::convertToProblemInfoDTO)
                .filter(problemInfoDTO -> {
                    boolean matchName = problemNameFilter == null || problemNameFilter.isEmpty() || problemInfoDTO.getProblemName().contains(problemNameFilter);
                    boolean matchComplexity = problemComplexityFilter == null || problemComplexityFilter.isEmpty() || problemInfoDTO.getProblemComplexity().equals(problemComplexityFilter);
                    boolean matchTheme = problemThemeFilter == null || problemThemeFilter.isEmpty() || problemInfoDTO.getProblemTheme().equals(problemThemeFilter);

                    return matchName && matchComplexity && matchTheme;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(problemInfoList);
    }


}
