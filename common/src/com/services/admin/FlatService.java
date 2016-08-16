package com.services.admin;

import com.dao.flats.FlatDaoImpl;
import com.entity.FlatsEntity;
import com.services.SaveOrUpdateObjectInputServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 10.08.2016.
 */
@Named
public class FlatService implements FlatServiceImpl {
    @Inject
    private FlatDaoImpl flatDaoImpl;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    private RemoveServiceImpl removeService;

    public List getFlats(String userName){
        return flatDaoImpl.getFlats(userName);
    }

    @Override
    public boolean removeFlat(long flatId,String userName) {
        FlatsEntity flat = (FlatsEntity)flatDaoImpl.getFlat(flatId);
        removeService.remove(flat,userName);
        saveOrUpdateObjectInputService.inputObject(flat);
        return true;
    }

}
