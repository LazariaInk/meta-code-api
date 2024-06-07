package com.lazaria.metacode.controller;


import com.lazaria.metacode.service.GcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gcs")
@CrossOrigin(origins = "http://localhost:3000")
public class ContentController {

    @Autowired
    private GcsService gcsService;

    @GetMapping("/topics")
    public List<String> getTopics() {
        return gcsService.getTopics();
    }

    @GetMapping("/topics/{topic}/chapters")
    public List<String> getChapters(@PathVariable String topic) {
        return gcsService.getChapters(topic);
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/lessons")
    public List<String> getLessons(@PathVariable String topic, @PathVariable String chapter) {
        return gcsService.getLessons(topic, chapter);
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/lessons/{lesson}")
    public String getLessonContent(@PathVariable String topic, @PathVariable String chapter, @PathVariable String lesson) {
        return gcsService.getLessonContent(topic, chapter, lesson);
    }
}
