package com.lazaria.metacode.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "lessonZone")
public class LessonZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonZoneId;

    @Column(name = "lessonTypeName")
    public String lessonZoneType;

    @Column(name = "lessonZoneContent")
    public String lessonZoneContent;
}
