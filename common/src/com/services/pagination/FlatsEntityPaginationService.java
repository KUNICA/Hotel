package com.services.pagination;

import com.dao.pagination.FlatsEntityPaginationDao;
import com.dataweb.MenuParametrs;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 04.08.2016.
 */
@Named
@Transactional
public class FlatsEntityPaginationService implements FlatsEntityPaginationServiceImpl {
    @Inject
    private FlatsEntityPaginationDao flatsEntityPaginationDao;

    @Override
    public List getObjects(Long start, Long end ,Double minPrice,Double maxPrice,Long persons,Long badrooms,Long bathrooms) {
        return flatsEntityPaginationDao.getObjects(start.intValue(), end.intValue(),minPrice,maxPrice,persons,badrooms,bathrooms);
    }

    @Override
    public Long getCountObjects(Long start, Long end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms) {
        return flatsEntityPaginationDao.getCountObjects(start.intValue(), end.intValue(),minPrice,maxPrice,persons,badrooms,bathrooms);
    }

    @Override
    public MenuParametrs getParametrObjects() {
        MenuParametrs param = new MenuParametrs();

        param.setMinPrice((Double)flatsEntityPaginationDao.getCountMin("price"));
        param.setMinPersons((Long)flatsEntityPaginationDao.getCountMin("slleeps"));
        param.setMinBadrooms((Long)flatsEntityPaginationDao.getCountMin("countRoums"));
        param.setMinBathrooms((Long)flatsEntityPaginationDao.getCountMin("bathrooms"));

        param.setMaxPrice((Double)flatsEntityPaginationDao.getCountMax("price"));
        param.setMaxPersons((Long)flatsEntityPaginationDao.getCountMax("slleeps"));
        param.setMaxBadrooms((Long)flatsEntityPaginationDao.getCountMax("countRoums"));
        param.setMaxBathrooms((Long)flatsEntityPaginationDao.getCountMax("bathrooms"));
        return param;
    }
}
