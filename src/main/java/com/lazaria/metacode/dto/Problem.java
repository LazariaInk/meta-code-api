package com.lazaria.metacode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int problemId;

    @Column(name = "sponsorName")
    private String problemName;

    @Column(name = "problemContent")
    private String problemContent;

    @Column(name = "problemSolution")
    private String problemSolution;

    @Column(name = "problemImage")
    private String problemImage;

    @Enumerated(EnumType.ORDINAL)
    private ProblemComplexity problemComplexity;

    @Enumerated(EnumType.STRING)
    private ProblemTheme problemTheme;


    public enum ProblemComplexity {
        VERY_EASY,
        EASY,
        MEDIUM,
        HARD,
        VERY_HARD,
        IMPOSSIBLE
    }

    public enum ProblemTheme {
        BINARY_SEARCH,
        GEOMETRY,
        TWO_INDICES,
        MATRIX,
        DYNAMIC_PROGRAMMING,
        LONG_MATH,
        GREEDY_ALGORITHM,
        BEGINNER,
        COMBINATORICS,
        MATHEMATICAL_MODEL,
        SIMPLE_MATHEMATICAL,
        STRING,
    }

}



