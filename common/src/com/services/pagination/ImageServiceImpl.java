package com.services.pagination;

import com.entity.ImagesEntity;

import java.util.List;

/**
 * Created by user on 05.08.2016.
 */
public interface ImageServiceImpl {
    ImagesEntity getImageFlat(Long id);
    ImagesEntity getImageGalary(Long id);
    List getImages(Long flatId);
}
