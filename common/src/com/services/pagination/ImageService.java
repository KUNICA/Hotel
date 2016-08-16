package com.services.pagination;

import com.dao.ObjectDaoImpl;
import com.dao.pagination.ImagesDaoImpl;
import com.entity.ImagesEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 05.08.2016.
 */
@Named
@Service
public class ImageService implements ImageServiceImpl {

    @Named("imageDao")
    @Inject
    private ImagesDaoImpl imageDao;

    @Named("gallaryDao")
    @Inject
    private ObjectDaoImpl gallaryDao;

    @Override
    public ImagesEntity getImageFlat(Long id) {
     return (ImagesEntity)imageDao.getObject(id);
    }

    @Override
    public ImagesEntity getImageGalary(Long id) {
        return (ImagesEntity)gallaryDao.getObject(id);
    }

    public List getImages(Long flatId) {
        return imageDao.getImages(flatId);
    }
}
