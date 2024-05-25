package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.Sponsor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sponsor")
public class PublicSponsorController {

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Sponsor> findAllSponsors() {
        return sponsorService.findAllSponsors();
    }

}
