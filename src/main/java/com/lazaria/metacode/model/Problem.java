package com.lazaria.metacode.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

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

    @Column(name="problemName")
    private String problemName;

    @Column(name = "problemContent",length = 5000)
    private String problemContent;

    @Column(name = "problemSolution", length = 5000)
    private String problemSolution;

    @Enumerated(EnumType.STRING)
    @Column(name = "problemComplexity")
    private ProblemComplexity problemComplexity;

    @Enumerated(EnumType.STRING)
    @Column(name="problemTheme")
    private ProblemTheme problemTheme;


    public enum ProblemComplexity {
        FOARTE_USOR,
        USOR,
        MEDIU,
        COMPLICAT,
        STEVE_WOZNIAK
    }

    public enum ProblemTheme {
        CAUTARE_BINARA,
        GEOMETRIE,
        MASIV_PATRAT,
        PROGRAMARE_DINAMICA,
        ALGORITMUL_LACOM,
        INCEPATORI,
        COMBINATORICA,
        MODELARE,
        STRING,
        RECURSIE,
        SORTARE,
        STRUCTURI_DE_DATE,
        TEOREMA_GRAFELOR
    }

}



