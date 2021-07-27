package com.shield.images.repository;

import com.shield.images.model.dto.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> getByAlbumId(Integer albumId);
}
