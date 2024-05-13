package com.lazaria.metacode.controller;

import com.lazaria.metacode.model.FabricaDeCoduriInfo;
import com.lazaria.metacode.service.FabricaDeCoduriInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/fabrica-de-coduri-info")
public class FabricaDeCoduriInfoController {

    private final FabricaDeCoduriInfoService fabricaDeCoduriInfoService;

    @Autowired
    public FabricaDeCoduriInfoController(FabricaDeCoduriInfoService fabricaDeCoduriInfoService) {
        this.fabricaDeCoduriInfoService = fabricaDeCoduriInfoService;
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> getFabricaDeCoduriInfoById(@PathVariable int id) {
        FabricaDeCoduriInfo fabricaDeCoduriInfo = fabricaDeCoduriInfoService.getFabricaDeCoduriInfoById(id);
        if (fabricaDeCoduriInfo != null) {
            return ResponseEntity.ok(fabricaDeCoduriInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/about-footer")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateAboutFooter(@PathVariable int id, @RequestBody String newAboutFooter) {
        return updateField(id, "aboutFooter", newAboutFooter);
    }

    @PutMapping("/{id}/donate-footer-content")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateDonateFooterContent(@PathVariable int id, @RequestBody String newDonateFooterContent) {
        System.out.println("s-a ajuns");
        return updateField(id, "donateFooterContent", newDonateFooterContent);
    }

    @PutMapping("/{id}/donate-content")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateDonateContent(@PathVariable int id, @RequestBody String newDonateContent) {
        System.out.println("s-a ajuns");
        return updateField(id, "donateContent", newDonateContent);
    }

    @PutMapping("/{id}/donate-title")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateDonateTitle(@PathVariable int id, @RequestBody String newDonateTitle) {
        System.out.println("Sa ajuns");
        return updateField(id, "donateTitle", newDonateTitle);
    }

    @PutMapping("/{id}/title-home")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateTitleHome(@PathVariable int id, @RequestBody String newTitleHome) {
        System.out.println("s-a ajuns");
        return updateField(id, "titleHome", newTitleHome);
    }

    @PutMapping("/{id}/contact-footer-content")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateContactFooterContent(@PathVariable int id, @RequestBody String newContactFooterContent) {
        return updateField(id, "contactFooterContent", newContactFooterContent);
    }

    @PutMapping("/{id}/discord-link")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateDiscordLink(@PathVariable int id, @RequestBody String newDiscordLink) {
        return updateField(id, "discordLink", newDiscordLink);
    }

    @PutMapping("/{id}/tiktok-link")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateTikTokLink(@PathVariable int id, @RequestBody String newTikTokLink) {
        return updateField(id, "tikTokLink", newTikTokLink);
    }

    @PutMapping("/{id}/instagram-link")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateInstagramLink(@PathVariable int id, @RequestBody String newInstagramLink) {
        return updateField(id, "instagramLink", newInstagramLink);
    }

    @PutMapping("/{id}/youtube-link")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateYoutubeLink(@PathVariable int id, @RequestBody String newYoutubeLink) {
        return updateField(id, "youtubeLink", newYoutubeLink);
    }

    @PutMapping("/{id}/owner-info")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateOwnerInfo(@PathVariable int id, @RequestBody String newOwnerInfo) {
        return updateField(id, "ownerInfo", newOwnerInfo);
    }

    @PutMapping("/{id}/motivational-message")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateMotivationalMessage(@PathVariable int id, @RequestBody String newMotivationalMessage) {
        return updateField(id, "motivationalMessage", newMotivationalMessage);
    }

    @PutMapping("/{id}/info-home-message")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateInfoHomeMessage(@PathVariable int id, @RequestBody String newInfoHomeMessage) {
        return updateField(id, "infoHomeMessage", newInfoHomeMessage);
    }

    @PutMapping("/{id}/intro-home-message")
    @CrossOrigin(origins = "https://www.fabricadecoduri.com")
    public ResponseEntity<FabricaDeCoduriInfo> updateIntroHomeMessage(@PathVariable int id, @RequestBody String newIntroHomeMessage) {
        return updateField(id, "introHomeMessage", newIntroHomeMessage);
    }

    private ResponseEntity<FabricaDeCoduriInfo> updateField(int id, String fieldName, String newValue) {
        FabricaDeCoduriInfo updatedFabricaDeCoduriInfo = fabricaDeCoduriInfoService.updateField(id, fieldName, newValue);

        if (updatedFabricaDeCoduriInfo != null) {
            return ResponseEntity.ok(updatedFabricaDeCoduriInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
