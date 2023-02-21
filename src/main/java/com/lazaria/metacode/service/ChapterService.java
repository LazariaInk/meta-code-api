package com.lazaria.metacode.service;

import com.lazaria.metacode.model.Chapter;
import com.lazaria.metacode.model.Topic;
import com.lazaria.metacode.repository.ChapterRepository;
import com.lazaria.metacode.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    TopicRepository topicRepository;

    public List<Chapter> getAllChaptersForSpecificTopic(int topicId) {
        return topicRepository.findById(topicId).get().getChapters();
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Optional<Topic> addChapterToTopic(Chapter newChapter, int topicId) {

        chapterRepository.save(newChapter);

        return topicRepository.findById(topicId)
                .map(topic -> {
                    topic.getChapters().add(newChapter);
                    return topicRepository.save(topic);
                });
    }

    public Optional<Chapter> editChapter(Chapter editedChapter, int chapterId) {
        return chapterRepository.findById(chapterId)
                .map(chapter -> {
                    chapter.setChapterName(editedChapter.getChapterName());
                    return chapterRepository.save(chapter);
                });
    }

    public void deleteChapterById(int chapterId) {
        chapterRepository.deleteById((chapterId));
    }

}
