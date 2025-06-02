package com.exemple.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.exemple.memes.EntityMemes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/memes")
public class ControllerMemes {

    @Autowired
    private ServiceMemes serviceMemes;

    @Autowired
    private RepositoryMemes repositoryMemes;

    public ControllerMemes(ServiceMemes serviceMemes, RepositoryMemes repositoryMemes) {
        this.serviceMemes = serviceMemes;
        this.repositoryMemes = repositoryMemes;
    }

    @GetMapping("/{id}")
    public EntityMemes getMemes(@PathVariable Long id) {
        return serviceMemes.getMemeById(id);
    }

    @GetMapping
    public ResponseEntity<List<EntityMemes>> getAllMemes() {
        List<EntityMemes> memes = serviceMemes.getAllMemes();
        return ResponseEntity.ok(memes);
    }

    @PostMapping("/test")
    public ResponseEntity<EntityMemes> saveTest(@RequestBody String nom) {
        EntityMemes entity = new EntityMemes();
        entity.setNom(nom);
        return ResponseEntity.ok(repositoryMemes.save(entity));
    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String fileName) throws IOException {
        Path imagePath = Paths.get("FUNNYMEMES").resolve(fileName);
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            String contentType = Files.probeContentType(imagePath);
            return ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/save")
    public ResponseEntity<String> uploadImages(
            @PathVariable Long id,
            @RequestParam("images") List<MultipartFile> images) throws IOException {

        EntityMemes memes = serviceMemes.getMemeById(id);

        for (MultipartFile file : images) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("FUNNYMEMES");
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            memes.getImagePaths().add("com/appli/FUNNYMEMES/" + fileName);
        }

        serviceMemes.addMemes(memes);
        return ResponseEntity.ok("Images uploadées avec succès !");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveMeme(@RequestBody EntityMemes meme) {
        serviceMemes.addMemes(meme);  // Save the entity directly
        return ResponseEntity.ok("Mème enregistré avec succès !");
    }

    @PutMapping("/{id}/add-text")
    public ResponseEntity<String> addTextToMeme(
            @PathVariable Long id,
            @RequestParam String topText,
            @RequestParam String bottomText) throws IOException {

        EntityMemes meme = serviceMemes.getMemeById(id);
        for (String imagePath : meme.getImagePaths()) {
            File file = new File(imagePath);
            BufferedImage modified = serviceMemes.addTextToImage(file, topText, bottomText);
            ImageIO.write(modified, "jpg", new File(imagePath));
        }
        meme.setTopText(topText);
        meme.setBottomText(bottomText);
        serviceMemes.addMemes(meme);
        return ResponseEntity.ok("Texte ajouté avec succès !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeme(@PathVariable Long id) {
        serviceMemes.deleteMemes(id);
        return ResponseEntity.ok("Mème supprimé !");
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadMeme(@PathVariable Long id) throws IOException {
        EntityMemes meme = serviceMemes.getMemeById(id);
        Path filePath = Paths.get(meme.getImagePaths().get(0));
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filePath.getFileName())
                .body(resource);
    }
}
