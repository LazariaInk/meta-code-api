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

    @Column(name = "aboutFooterContent",columnDefinition="VARCHAR(1000)")
    private String aboutFooter; //este

    @Column(name="donateFooterContent",columnDefinition="VARCHAR(1000)")
    private String donateFooterContent; //este

    @Column(name="contactFooterContent",columnDefinition="VARCHAR(1000)")
    private String contactFooterContent; //este

    @Column(name = "discordLink",columnDefinition="VARCHAR(1000)")
    private String discordLink; // este

    @Column(name="tikTokLink",columnDefinition="VARCHAR(1000)")
    private String tikTokLink; // este

    @Column(name = "instagramLink",columnDefinition="VARCHAR(1000)")
    private String instagramLink; //este

    @Column(name = "youtubeLink",columnDefinition="VARCHAR(1000)")
    private String youtubeLink; // este

    @Column(name = "ownerInfo",columnDefinition="VARCHAR(1000)")
    private String ownerInfo; // este

    @Column(name = "motivationalMessage",columnDefinition="VARCHAR(1000)")
    private String motivationalMessage; //este

    @Column(name="infoHomeMessage",columnDefinition="VARCHAR(1000)")
    private String infoHomeMessage; // este

    @Column(name="introHomeMessage",columnDefinition="VARCHAR(1000)")
    private String introHomeMessage; // este

    @Column(name="titleHome",columnDefinition="VARCHAR(1000)")
    private String titleHome; // este

    @Column(name="donateTitle",columnDefinition="VARCHAR(1000)")
    private String donateTitle; //nui

    @Column(name = "donateContent",columnDefinition="VARCHAR(1000)")
    private String donateContent; //nui

}
