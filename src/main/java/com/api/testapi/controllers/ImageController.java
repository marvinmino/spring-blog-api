package com.api.testapi.controllers;

import com.api.testapi.models.Image;
import com.api.testapi.repositories.BlogRepository;
import com.api.testapi.repositories.ImageRepository;
import com.api.testapi.responses.ImageUploadResponse;
import com.api.testapi.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class ImageController {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    BlogRepository blogRepository;

    @PostMapping("/upload/image/{id}")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable(value="id") Long id)
            throws IOException {

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .blog(blogRepository.findById(id).get())
                .image(ImageUtility.compressImage(file.getBytes())).build());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{id}"})
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{id}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}
