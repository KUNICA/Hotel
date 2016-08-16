package com.services.admin;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Named;
import java.io.*;

/**
 * Created by user on 10.08.2016.
 */
@Named
public class ImageFileService implements ImageFileServiceImpl{

    static String LOCATION = "c:\\Users\\user\\IdeaProjects\\Hotel\\resources\\images\\flats\\";
    static String LOCATION_TARGET = "c:\\Users\\user\\IdeaProjects\\Hotel_war\\images\\flats\\";

    public void validateImage(MultipartFile image) throws ImageUploadException {
        if(!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    public void saveFileImage(String filename, MultipartFile image) throws ImageUploadException {
        try {
            File file = new File(LOCATION + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            File fileT= new File(LOCATION_TARGET + filename);
            FileUtils.writeByteArrayToFile(fileT, image.getBytes());
        } catch (IOException e) {
            throw new ImageUploadException("Unable to save image");
        }

    }


}
