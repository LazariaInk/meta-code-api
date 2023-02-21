package com.lazaria.metacode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chapterId;

    @Column(name = "chapterName")
    public String chapterName;

    @OneToMany(targetEntity = Lesson.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_lesson_fk", referencedColumnName = "chapterId")
    private List<Lesson> lessons;

}


