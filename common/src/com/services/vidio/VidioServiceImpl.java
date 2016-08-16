package com.services.vidio;

import com.entity.VideoEntity;

import java.util.List;

/**
 * Created by user on 05.08.2016.
 */
public interface VidioServiceImpl {
    List getVidios(Long id);
    VideoEntity getVidio(Long id);
}
