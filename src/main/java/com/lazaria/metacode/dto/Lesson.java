package com.lazaria.metacode.dto;

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
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;

    @Column(name = "lessonName")
    public String lessonName;

    @OneToMany(targetEntity = LessonZone.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "lesson_lessonZone_fk", referencedColumnName = "lessonId")
    private List<LessonZone> lessonZones;

}
