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
    @CrossOrigin(origins = "http://localhost:3000")
    public List<String> getTopics() {
        return gcsService.getTopics();
    }

    @GetMapping("/topics/{topic}/chapters")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<String> getChapters(@PathVariable String topic) {
        return gcsService.getChapters(topic);
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/lessons")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<String> getLessons(@PathVariable String topic, @PathVariable String chapter) {
        return gcsService.getLessons(topic, chapter);
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/lessons/{lesson}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getLessonContent(@PathVariable String topic, @PathVariable String chapter, @PathVariable String lesson) {
        return gcsService.getLessonContent(topic, chapter, lesson);
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/lessons/{lesson}/next")
    public String getNextLesson(@PathVariable String topic, @PathVariable String chapter, @PathVariable String lesson) {
        List<String> lessons = gcsService.getLessons(topic, chapter);
        int index = lessons.indexOf(lesson);

        if (index != -1 && index < lessons.size() - 1) {
            return lessons.get(index + 1);
        }
        return null;
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/lessons/{lesson}/previous")
    public String getPreviousLesson(@PathVariable String topic, @PathVariable String chapter, @PathVariable String lesson) {
        List<String> lessons = gcsService.getLessons(topic, chapter);
        int index = lessons.indexOf(lesson);

        if (index > 0) {
            return lessons.get(index - 1);
        }
        return null;
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/next")
    public String getNextChapterFirstLesson(@PathVariable String topic, @PathVariable String chapter) {
        List<String> chapters = gcsService.getChapters(topic);
        int index = chapters.indexOf(chapter);
        if (index != -1 && index < chapters.size() - 1) {
            String nextChapter = chapters.get(index + 1);
            List<String> lessonsInNextChapter = gcsService.getLessons(topic, nextChapter);
            if (!lessonsInNextChapter.isEmpty()) {
                return nextChapter + "|" + lessonsInNextChapter.get(0);
            }
        }
        return null;
    }

    @GetMapping("/topics/{topic}/chapters/{chapter}/previous")
    public String getPreviousChapterLastLesson(@PathVariable String topic, @PathVariable String chapter) {
        List<String> chapters = gcsService.getChapters(topic);
        int index = chapters.indexOf(chapter);

        if (index > 0) {
            String previousChapter = chapters.get(index - 1);
            List<String> lessonsInPreviousChapter = gcsService.getLessons(topic, previousChapter);
            if (!lessonsInPreviousChapter.isEmpty()) {
                return previousChapter + "|" + lessonsInPreviousChapter.get(lessonsInPreviousChapter.size() - 1);
            }
        }
        return null;
    }



}
