package com.shield.images.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="images")
public class Image {
    @Id
    private Integer id;
    private Integer albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
    private Date timeDownload;
    private String localPath;
    private double sizeFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Date getTimeDownload() {
        return timeDownload;
    }

    public void setTimeDownload(Date timeDownload) {
        this.timeDownload = timeDownload;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public double getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(double sizeFile) {
        this.sizeFile = sizeFile;
    }
}
