package com.lazaria.metacode.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LessonDTO")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;

    @Column(name = "lessonName")
    public String lessonName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapterId")
    public Chapter chapter;


}
