package com.lazaria.metacode.service;

import com.lazaria.metacode.model.FabricaDeCoduriInfo;
import com.lazaria.metacode.repository.FabricaDeCoduriInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabricaDeCoduriInfoService {

    private final FabricaDeCoduriInfoRepository fabricaDeCoduriInfoRepository;

    @Autowired
    public FabricaDeCoduriInfoService(FabricaDeCoduriInfoRepository fabricaDeCoduriInfoRepository) {
        this.fabricaDeCoduriInfoRepository = fabricaDeCoduriInfoRepository;
    }

    public FabricaDeCoduriInfo getFabricaDeCoduriInfoById(int id) {
        return fabricaDeCoduriInfoRepository.findById(id).orElse(null);
    }

    public FabricaDeCoduriInfo updateAboutFooter(int id, String newAboutFooter) {
        return updateField(id, "aboutFooter", newAboutFooter);
    }

    public FabricaDeCoduriInfo updateDonateFooterContent(int id, String newDonateFooterContent) {
        return updateField(id, "donateFooterContent", newDonateFooterContent);
    }

    public FabricaDeCoduriInfo updateContactFooterContent(int id, String newContactFooterContent) {
        return updateField(id, "contactFooterContent", newContactFooterContent);
    }

    public FabricaDeCoduriInfo updateDiscordLink(int id, String newDiscordLink) {
        return updateField(id, "discordLink", newDiscordLink);
    }

    public FabricaDeCoduriInfo updateTikTokLink(int id, String newTikTokLink) {
        return updateField(id, "tikTokLink", newTikTokLink);
    }

    public FabricaDeCoduriInfo updateInstagramLink(int id, String newInstagramLink) {
        return updateField(id, "instagramLink", newInstagramLink);
    }

    public FabricaDeCoduriInfo updateYoutubeLink(int id, String newYoutubeLink) {
        return updateField(id, "youtubeLink", newYoutubeLink);
    }

    public FabricaDeCoduriInfo updateOwnerInfo(int id, String newOwnerInfo) {
        return updateField(id, "ownerInfo", newOwnerInfo);
    }

    public FabricaDeCoduriInfo updateMotivationalMessage(int id, String newMotivationalMessage) {
        return updateField(id, "motivationalMessage", newMotivationalMessage);
    }

    public FabricaDeCoduriInfo updateInfoHomeMessage(int id, String newInfoHomeMessage) {
        return updateField(id, "infoHomeMessage", newInfoHomeMessage);
    }

    public FabricaDeCoduriInfo updateIntroHomeMessage(int id, String newIntroHomeMessage) {
        return updateField(id, "introHomeMessage", newIntroHomeMessage);
    }

    public FabricaDeCoduriInfo updateTitleHome(int id, String newTitleHome) {
        return updateField(id, "titleHome", newTitleHome);
    }

    public FabricaDeCoduriInfo updateField(int id, String fieldName, String newValue) {
        FabricaDeCoduriInfo fabricaDeCoduriInfo = fabricaDeCoduriInfoRepository.findById(id).orElse(null);

        if (fabricaDeCoduriInfo != null) {
            switch (fieldName) {
                case "aboutFooter":
                    fabricaDeCoduriInfo.setAboutFooter(newValue);
                    break;
                case "donateFooterContent":
                    fabricaDeCoduriInfo.setDonateFooterContent(newValue);
                    break;
                case "contactFooterContent":
                    fabricaDeCoduriInfo.setContactFooterContent(newValue);
                    break;
                case "discordLink":
                    fabricaDeCoduriInfo.setDiscordLink(newValue);
                    break;
                case "tikTokLink":
                    fabricaDeCoduriInfo.setTikTokLink(newValue);
                    break;
                case "instagramLink":
                    fabricaDeCoduriInfo.setInstagramLink(newValue);
                    break;
                case "youtubeLink":
                    fabricaDeCoduriInfo.setYoutubeLink(newValue);
                    break;
                case "ownerInfo":
                    fabricaDeCoduriInfo.setOwnerInfo(newValue);
                    break;
                case "motivationalMessage":
                    fabricaDeCoduriInfo.setMotivationalMessage(newValue);
                    break;
                case "infoHomeMessage":
                    fabricaDeCoduriInfo.setInfoHomeMessage(newValue);
                    break;
                case "titleHome":
                    fabricaDeCoduriInfo.setTitleHome(newValue);
                    break;
                case "donateTitle":
                    fabricaDeCoduriInfo.setDonateTitle(newValue);
                    break;
                case "donateContent":
                    fabricaDeCoduriInfo.setDonateContent(newValue);
                    break;
                case "introHomeMessage":
                    fabricaDeCoduriInfo.setIntroHomeMessage(newValue);
                    break;
                default:
                    return null;
            }
            return fabricaDeCoduriInfoRepository.save(fabricaDeCoduriInfo);
        }
        return null;
    }
}
