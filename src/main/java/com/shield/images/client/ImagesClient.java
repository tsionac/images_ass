package com.shield.images.client;

import com.shield.images.model.client.ExternalImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ImagesClient", url="${external.images.url}")
public interface ImagesClient {

    @GetMapping("/photo.txt")
    List<ExternalImage> getImages();

    @GetMapping("/")
    List<ExternalImage> getImage(@PathVariable("imagePath") String imagePath);

}
