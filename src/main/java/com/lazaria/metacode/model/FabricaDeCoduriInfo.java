package com.lazaria.metacode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "fabricadecoduriinfo")
public class FabricaDeCoduriInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fabricaDeCoduriInfoId;

    @Column(name = "aboutFooterContent")
    private String aboutFooter; //este

    @Column(name="donateFooterContent")
    private String donateFooterContent; //este

    @Column(name="contactFooterContent")
    private String contactFooterContent; //este

    @Column(name = "discordLink")
    private String discordLink; // este

    @Column(name="tikTokLink")
    private String tikTokLink; // este

    @Column(name = "instagramLink")
    private String instagramLink; //este

    @Column(name = "youtubeLink")
    private String youtubeLink; // este

    @Column(name = "ownerInfo")
    private String ownerInfo; // este

    @Column(name = "motivationalMessage")
    private String motivationalMessage; //este

    @Column(name="infoHomeMessage")
    private String infoHomeMessage; // este

    @Column(name="introHomeMessage")
    private String introHomeMessage; // este

    @Column(name="titleHome")
    private String titleHome; // este

    @Column(name="donateTitle")
    private String donateTitle; //nui

    @Column(name = "donateContent")
    private String donateContent; //nui

}
