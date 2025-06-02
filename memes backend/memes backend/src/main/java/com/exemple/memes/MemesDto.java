package com.exemple.memes;

import java.util.Date;
import java.util.List;

public class MemesDto {

    private Long id;
    private String topText;
    private String bottomText;
    private String nom;
    private Date dateCreation;
    private Date dateDeModification;
    private List<String> imagePaths;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateDeModification() {
        return dateDeModification;
    }

    public void setDateDeModification(Date dateDeModification) {
        this.dateDeModification = dateDeModification;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
