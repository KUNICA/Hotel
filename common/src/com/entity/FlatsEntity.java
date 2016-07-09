package com.entity;

import javax.persistence.*;

/**
 * Created by user on 30.06.2016.
 */
@Entity
@Table(name = "flats", schema = "hotel_work", catalog = "")
public class FlatsEntity {
    private int id;
    private int countRoums;
    private int countFloors;
    private Double price;
    private Integer slleeps;
    private Integer slleepsTo;
    private Integer bathrooms;
    private Integer galleryId;
    private Integer videoLibraryId;
    private String shortDescription;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "count_roums", nullable = false)
    public int getCountRoums() {
        return countRoums;
    }

    public void setCountRoums(int countRoums) {
        this.countRoums = countRoums;
    }

    @Basic
    @Column(name = "count_floors", nullable = false)
    public int getCountFloors() {
        return countFloors;
    }

    public void setCountFloors(int countFloors) {
        this.countFloors = countFloors;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 5)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "slleeps", nullable = true)
    public Integer getSlleeps() {
        return slleeps;
    }

    public void setSlleeps(Integer slleeps) {
        this.slleeps = slleeps;
    }

    @Basic
    @Column(name = "slleeps_to", nullable = true)
    public Integer getSlleepsTo() {
        return slleepsTo;
    }

    public void setSlleepsTo(Integer slleepsTo) {
        this.slleepsTo = slleepsTo;
    }

    @Basic
    @Column(name = "bathrooms", nullable = true)
    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    @Basic
    @Column(name = "gallery_id", nullable = true)
    public Integer getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Integer galleryId) {
        this.galleryId = galleryId;
    }

    @Basic
    @Column(name = "video_library_id", nullable = true)
    public Integer getVideoLibraryId() {
        return videoLibraryId;
    }

    public void setVideoLibraryId(Integer videoLibraryId) {
        this.videoLibraryId = videoLibraryId;
    }

    @Basic
    @Column(name = "short_description", nullable = true, length = -1)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatsEntity that = (FlatsEntity) o;

        if (id != that.id) return false;
        if (countRoums != that.countRoums) return false;
        if (countFloors != that.countFloors) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (slleeps != null ? !slleeps.equals(that.slleeps) : that.slleeps != null) return false;
        if (slleepsTo != null ? !slleepsTo.equals(that.slleepsTo) : that.slleepsTo != null) return false;
        if (bathrooms != null ? !bathrooms.equals(that.bathrooms) : that.bathrooms != null) return false;
        if (galleryId != null ? !galleryId.equals(that.galleryId) : that.galleryId != null) return false;
        if (videoLibraryId != null ? !videoLibraryId.equals(that.videoLibraryId) : that.videoLibraryId != null)
            return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + countRoums;
        result = 31 * result + countFloors;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (slleeps != null ? slleeps.hashCode() : 0);
        result = 31 * result + (slleepsTo != null ? slleepsTo.hashCode() : 0);
        result = 31 * result + (bathrooms != null ? bathrooms.hashCode() : 0);
        result = 31 * result + (galleryId != null ? galleryId.hashCode() : 0);
        result = 31 * result + (videoLibraryId != null ? videoLibraryId.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        return result;
    }
}
