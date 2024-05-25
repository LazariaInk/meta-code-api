package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Topic;
import com.lazaria.metacode.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/topic")
public class PublicTopicsController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Topic> findAllTopics() {
        return topicService.findAllTopics();
    }
}