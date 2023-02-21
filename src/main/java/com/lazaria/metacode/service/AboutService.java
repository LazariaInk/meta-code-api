package com.lazaria.metacode.service;

import com.lazaria.metacode.model.About;
import com.lazaria.metacode.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AboutService {

    @Autowired
    private AboutRepository aboutRepository;

    public Optional<About> findAbout() {
        return aboutRepository.findById(1);
    }

    public About updateAbout(About about) {
        about.setId(1);
        return aboutRepository.save(about);
    }
}
