package com.services.vidio;

import com.dao.video.VideoDaoImpl;
import com.entity.VideoEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 05.08.2016.
 */
@Named
public class VideoService implements VidioServiceImpl{

    @Inject
    private VideoDaoImpl videoDao;

    @Override
    public List getVidios(Long id) {
        return videoDao.getVidios(id);
    }

    @Override
    public VideoEntity getVidio(Long id) {
        return videoDao.getVidio(id);
    }
}
