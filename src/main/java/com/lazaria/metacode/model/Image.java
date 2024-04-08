    package com.lazaria.metacode.model;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Table(name = "image")
    public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "file_name")
        private String fileName;

        @Lob
        private String base64Data;

        @ManyToOne
        @JoinColumn(name = "lesson_id")
        private Lesson lesson;
    }
