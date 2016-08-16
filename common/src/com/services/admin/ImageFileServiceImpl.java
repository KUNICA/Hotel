package com.services.admin;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by user on 10.08.2016.
 */
public interface ImageFileServiceImpl {
    void validateImage(MultipartFile image) throws ImageUploadException;
    void saveFileImage(String filename, MultipartFile image) throws ImageUploadException;
}
