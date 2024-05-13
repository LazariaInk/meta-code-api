package com.lazaria.metacode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Lob
    @Column(name="lessonContent",columnDefinition="LONGBLOB")
    public String lessonContent;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @JsonIgnore // Add this annotation to prevent serialization of images
    private List<Image> images;

}
