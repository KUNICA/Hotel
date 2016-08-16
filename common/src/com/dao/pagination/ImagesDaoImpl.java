package com.dao.pagination;

import java.util.List;

/**
 * Created by user on 05.08.2016.
 */
public interface ImagesDaoImpl {
    List getImages(Long flatId);
    Object getObject(Long id);
}
