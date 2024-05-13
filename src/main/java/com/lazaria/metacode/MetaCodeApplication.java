package com.lazaria.metacode;

import com.lazaria.metacode.model.FabricaDeCoduriInfo;
import com.lazaria.metacode.repository.FabricaDeCoduriInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class MetaCodeApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MetaCodeApplication.class, args);
        FabricaDeCoduriInfoRepository repository = context.getBean(FabricaDeCoduriInfoRepository.class);

        // Crearea unui record cu toate câmpurile setate la 'test'
        FabricaDeCoduriInfo info = new FabricaDeCoduriInfo();
        info.setAboutFooter("test");
        info.setDonateFooterContent("test");
        info.setContactFooterContent("test");
        info.setDiscordLink("test");
        info.setTikTokLink("test");
        info.setInstagramLink("test");
        info.setYoutubeLink("test");
        info.setOwnerInfo("test");
        info.setMotivationalMessage("test");
        info.setInfoHomeMessage("test");
        info.setIntroHomeMessage("test");
        info.setTitleHome("test");
        info.setDonateTitle("test");
        info.setDonateContent("test");

        // Salvarea în baza de date
        repository.save(info);
    }
}
