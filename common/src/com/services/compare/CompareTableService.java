package com.services.compare;

import com.dao.compare.CompareDaoImpl;
import com.dao.flats.FlatDaoImpl;
import com.entity.FlatsEntity;
import com.entity.UsersFildsEntity;
import com.services.compare.ui.SearchFields;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 08.08.2016.
 */
@Named
@Service
public class CompareTableService implements CompareTableServiceImpl{
    @Inject
    private CompareDaoImpl compareDaoImpl;
    @Inject
    private FlatDaoImpl flatDao;

    @Override
    public List getFlats(String userName, Long FlatId) {
        UsersFildsEntity compare = (UsersFildsEntity)compareDaoImpl.getCompare(userName);
        List flatsArray =  SearchFields.getFlatsList(compare);
        List list = flatDao.getFlats(flatsArray);
        if(!flatsArray.contains(FlatId)) {
            FlatsEntity flatsEntity = (FlatsEntity) flatDao.getFlat(FlatId);
            list.add(flatsEntity);
        }
        return list;
    }
}
