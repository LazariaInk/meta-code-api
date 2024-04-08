package com.lazaria.metacode.model.dao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ImageDTO {
    private String fileName;
    private String base64Data;
}
