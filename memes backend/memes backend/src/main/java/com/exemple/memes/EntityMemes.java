package com.exemple.memes;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "memes")
public class EntityMemes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topText;
    private String bottomText;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String base64Image;
    private String nom;
    @CreationTimestamp
    private Date dateCreation = new Date();
    @UpdateTimestamp
    private Date dateDeModification = new Date();

    public EntityMemes() {
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

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
    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    @ElementCollection
    private List<String>imagePaths = new ArrayList<>();


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
}
