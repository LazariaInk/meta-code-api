package com.lazaria.metacode.controller;


import com.lazaria.metacode.model.Sponsor;
import com.lazaria.metacode.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/sponsor")
public class SponsorsController {

    @Autowired
    private SponsorService sponsorService;

    @PostMapping("/create")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.createSponsor(sponsor);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    public List<Sponsor> findAllSponsors() {
        return sponsorService.findAllSponsors();
    }

    @DeleteMapping("/delete/{sponsorId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    public void deleteSponsor(@PathVariable int sponsorId) {
        sponsorService.deleteSponsorById(sponsorId);
    }

    @PutMapping("edit/{sponsorId}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com/")
    public Optional<Sponsor> editSponsor(@RequestBody Sponsor sponsorForEdit, @PathVariable int sponsorId) {
        return sponsorService.editeSponsor(sponsorForEdit, sponsorId);
    }
}
