package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Topic;
import com.lazaria.metacode.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Optional<Topic> getTopicById(int id) {
        return topicRepository.findById(id);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public void addTopic(Topic topic) {
        if (topic.topicName == null) {
            return;
        }
        topicRepository.save(topic);
    }

    public void deleteTopic(int id) {
        topicRepository.deleteById(id);
    }

    public void editTopic(Topic topic) {

    }
}


