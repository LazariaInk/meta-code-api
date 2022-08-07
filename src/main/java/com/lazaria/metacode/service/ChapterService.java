package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Chapter;
import com.lazaria.metacode.dto.Topic;
import com.lazaria.metacode.repository.ChapterRepository;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

}
