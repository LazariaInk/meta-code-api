package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.About;
import com.lazaria.metacode.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @PutMapping("/update")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    public About updateAbout(@RequestBody About about) {

        return aboutService.updateAbout(about);
    }
}
