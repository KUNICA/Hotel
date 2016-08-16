package com.services.pagination;

import com.dataweb.MenuParametrs;

import java.util.List;

/**
 * Created by user on 04.08.2016.
 */
public interface FlatsEntityPaginationServiceImpl {
    List getObjects(Long start, Long end,Double minPrice,Double maxPrice,Long persons,Long badrooms,Long bathrooms);
    Long getCountObjects(Long start, Long end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms);
    MenuParametrs getParametrObjects();
}
