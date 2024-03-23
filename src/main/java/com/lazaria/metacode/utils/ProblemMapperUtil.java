package com.lazaria.metacode.utils;

import com.lazaria.metacode.model.Problem;
import com.lazaria.metacode.model.dao.ProblemInfoDTO;

public class ProblemMapperUtil {
    public static ProblemInfoDTO convertToProblemInfoDTO(Problem problem) {
        ProblemInfoDTO problemInfoDTO = new ProblemInfoDTO();
        problemInfoDTO.setProblemId(problem.getProblemId());
        problemInfoDTO.setProblemName(problem.getProblemName());
        problemInfoDTO.setProblemComplexity(problem.getProblemComplexity().toString());
        problemInfoDTO.setProblemTheme(problem.getProblemTheme().toString());
        return problemInfoDTO;
    }

    private ProblemMapperUtil(){}
}
