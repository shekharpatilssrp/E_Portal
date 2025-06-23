package com.plantandsoil.portal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "plant_cards")
public class PlantCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "more_info_url")
    private String moreInfoUrl;

    @Column(name = "soil_ph_min")
    private Double soilPhMin;

    @Column(name = "soil_ph_max")
    private Double soilPhMax;

    // Constructors
    public PlantCard() {}

    public PlantCard(String imageUrl, String title, String description, String moreInfoUrl, Double soilPhMin, Double soilPhMax) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.moreInfoUrl = moreInfoUrl;
        this.soilPhMin = soilPhMin;
        this.soilPhMax = soilPhMax;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoreInfoUrl() {
        return moreInfoUrl;
    }

    public void setMoreInfoUrl(String moreInfoUrl) {
        this.moreInfoUrl = moreInfoUrl;
    }

    public Double getSoilPhMin() {
        return soilPhMin;
    }

    public void setSoilPhMin(Double soilPhMin) {
        this.soilPhMin = soilPhMin;
    }

    public Double getSoilPhMax() {
        return soilPhMax;
    }

    public void setSoilPhMax(Double soilPhMax) {
        this.soilPhMax = soilPhMax;
    }
}
