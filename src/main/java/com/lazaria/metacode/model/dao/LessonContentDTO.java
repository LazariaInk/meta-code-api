package com.lazaria.metacode.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonContentDTO {
    private String lessonContent;
    private List<ImageDTO> images;
}
