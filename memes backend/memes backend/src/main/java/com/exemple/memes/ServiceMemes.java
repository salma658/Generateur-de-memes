package com.exemple.memes;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ServiceMemes {
    // Injection du Repository Ingredient
    private final RepositoryMemes repositorymemes;

    @Autowired
    public ServiceMemes (RepositoryMemes repositorymemes){
        this.repositorymemes= repositorymemes;

    }
    // Récuperer un meme avec son id
    public com.exemple.memes.EntityMemes getMemeById(Long id){
        return repositorymemes.findById(id).orElseThrow(() -> new RuntimeException("Meme non trouvé avec id : " + id));
    }

    // Ajouter un nouveau meme
    public EntityMemes addMemes(EntityMemes memes){
        return repositorymemes.save(memes);
    }

    // Récuperer tous les memes
    public List<EntityMemes> getAllMemes() {
        return repositorymemes.findAll();
    }


    // Mise a jour
    public EntityMemes updateMemes(Long id, EntityMemes newMemes){
        EntityMemes existingMemes = repositorymemes.findById(id).orElseThrow(()->new RuntimeException("Memes NON trouvé"));
                existingMemes.setImagePaths(newMemes.getImagePaths());
                return repositorymemes.save(existingMemes);
    }

    // Supression
    public void deleteMemes(Long id){
        if (!repositorymemes.existsById(id)) {
            throw new RuntimeException("Mèmes NON trouvé");
        }
        repositorymemes.deleteById(id);
    }
    // Editer l'image

    public BufferedImage addTextToImage(File imageFile, String topText, String bottomText) throws IOException {
        BufferedImage image = ImageIO.read(imageFile);
        Graphics2D g2d = image.createGraphics();

        g2d.setFont(new Font("Impact", Font.BOLD, 40));
        g2d.setColor(Color.WHITE);
        g2d.drawString(topText, 10, 40);
        g2d.drawString(bottomText, 10, image.getHeight() - 20);

        g2d.dispose();
        return image;
    }

}
