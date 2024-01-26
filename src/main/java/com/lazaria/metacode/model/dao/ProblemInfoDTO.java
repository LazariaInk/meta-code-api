package com.lazaria.metacode.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProblemInfoDTO {
    private int problemId;
    private String problemName;
    private String problemComplexity;
    private String problemTheme;

}
