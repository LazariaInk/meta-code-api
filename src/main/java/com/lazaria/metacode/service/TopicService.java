package com.lazaria.metacode.service;

import com.lazaria.metacode.model.Topic;
import com.lazaria.metacode.repository.ChapterRepository;
import com.lazaria.metacode.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    public void deleteTopic(int topicId) {
        topicRepository.deleteById(topicId);
    }

    public Optional<Topic> editeTopicName(Topic topicForEdit, int topicId) {
        return topicRepository.findById(topicId)
                .map(topic -> {
                    topic.setTopicName(topicForEdit.getTopicName());
                    return topicRepository.save(topic);
                });
    }

}



