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

    @Column(name = "aboutFooterContent",columnDefinition="VARCHAR")
    private String aboutFooter; //este

    @Column(name="donateFooterContent",columnDefinition="VARCHAR")
    private String donateFooterContent; //este

    @Column(name="contactFooterContent",columnDefinition="VARCHAR")
    private String contactFooterContent; //este

    @Column(name = "discordLink",columnDefinition="VARCHAR")
    private String discordLink; // este

    @Column(name="tikTokLink",columnDefinition="VARCHAR")
    private String tikTokLink; // este

    @Column(name = "instagramLink",columnDefinition="VARCHAR")
    private String instagramLink; //este

    @Column(name = "youtubeLink",columnDefinition="VARCHAR")
    private String youtubeLink; // este

    @Column(name = "ownerInfo",columnDefinition="VARCHAR")
    private String ownerInfo; // este

    @Column(name = "motivationalMessage",columnDefinition="VARCHAR")
    private String motivationalMessage; //este

    @Column(name="infoHomeMessage",columnDefinition="VARCHAR")
    private String infoHomeMessage; // este

    @Column(name="introHomeMessage",columnDefinition="VARCHAR")
    private String introHomeMessage; // este

    @Column(name="titleHome",columnDefinition="VARCHAR")
    private String titleHome; // este

    @Column(name="donateTitle",columnDefinition="VARCHAR")
    private String donateTitle; //nui

    @Column(name = "donateContent",columnDefinition="VARCHAR")
    private String donateContent; //nui

}
