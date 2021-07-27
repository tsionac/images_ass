package com.shield.images.service;

import com.shield.images.client.ImagesClient;
import com.shield.images.model.client.ExternalImage;
import com.shield.images.model.dto.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shield.images.repository.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagesService {

    private final ImagesClient imagesClient;
    private final ImageRepository imageRepository;

    public List<Image> getImages(Integer albumId){
        if(albumId == null) {
            List<ExternalImage> externalImages = imagesClient.getImages();
            List<Image> dbImages = imageRepository.findAll();
            if (externalImages.size() > dbImages.size()) {
                Set<Integer> existingIds = dbImages.stream().map(img -> img.getId()).collect(Collectors.toSet());
                List<ExternalImage> newExternalImages = externalImages.stream().filter(img -> !existingIds.contains(img.getId())).collect(Collectors.toList());
                List<Image> imagesList = generateImageFromExt(newExternalImages);
                imageRepository.saveAll(imagesList);
            }
            return imageRepository.findAll();
        }
        else{
            return imageRepository.getByAlbumId(albumId);
        }
    }

    private List<Image> generateImageFromExt(List<ExternalImage> newExternalImages) {
        BufferedImage bufferedImage =null;
        double fileSizeInBytes = 0;
        List<Image> newImages = new ArrayList<>();
        for(ExternalImage externalImage: newExternalImages){
            try{
                URL url =new URL(externalImage.getThumbnailUrl());
                // read the url
                bufferedImage = ImageIO.read(url);
                File file = new File("tmp/"+externalImage.getId().toString()+".png");
                fileSizeInBytes = file.length();//TODO not sure its just here and ask about new
                ImageIO.write(bufferedImage, "png",file);
            }
            catch(IOException e){
                e.printStackTrace();
            }

            Image image = new Image();
            image.setId(externalImage.getId());
            image.setAlbumId(externalImage.getAlbumId());
            image.setTitle(externalImage.getTitle());
            image.setUrl(externalImage.getUrl());
            image.setThumbnailUrl(externalImage.getThumbnailUrl());
            image.setTimeDownload(new Date());
            image.setLocalPath("/tmp/"+externalImage.getUrl());
            image.setSizeFile(fileSizeInBytes);
            newImages.add(image);


        }
        return newImages;
    }

    public byte[] downloadImage(Integer id) throws IOException {
        byte [] data = null;

        BufferedImage bImage = ImageIO.read(new File("tmp/"+id+".png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos );
        data = bos.toByteArray();

        return data;


    }

}
