package com.shield.images.controller;

import com.shield.images.model.client.ExternalImage;
import com.shield.images.model.dto.Image;
import com.shield.images.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ImagesController {

    private final ImagesService imagesService;

    @GetMapping(value = "/v1/images", produces = {"application/json"})
    public ResponseEntity<List<Image>> getImages(@RequestParam(value="albumId", required = false) Integer albumId){
        return ResponseEntity.ok(imagesService.getImages(albumId));
    }

    @GetMapping(value = "/v1/images/{id}", produces = MediaType.IMAGE_PNG_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<byte []>  getImage(@PathVariable(value="id") Integer id) throws IOException {
        byte[] image = imagesService.downloadImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);


    }


    
}
