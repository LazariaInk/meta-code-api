package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Sponsor;
import com.lazaria.metacode.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;


    public Sponsor createSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    public List<Sponsor> findAllSponsors() {
        return sponsorRepository.findAll();
    }

    public void deleteSponsorById(int sponsorId) {
        sponsorRepository.deleteById(sponsorId);
    }

    public Optional<Sponsor> editeTopicName(Sponsor sponsorForEdit, int sponsorId) {
        return sponsorRepository.findById(sponsorId)
                .map(sponsor -> {
                    sponsor.setTopicName(sponsorForEdit.getTopicName());
                    sponsor.setSponsorLink(sponsorForEdit.getSponsorLink());
                    return sponsorRepository.save(sponsor);
                });
    }
}
