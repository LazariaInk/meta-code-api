package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chapter")
public class PublicChaptersController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/{topicId}/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Chapter> findAllChapters(@PathVariable int topicId) {
        return chapterService.getAllChaptersForSpecificTopic(topicId);
    }
}
