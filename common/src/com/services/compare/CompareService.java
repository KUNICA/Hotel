package com.services.compare;

import com.dao.compare.CompareDaoImpl;
import com.entity.UsersFildsEntity;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.compare.ui.SearchFields;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by user on 07.08.2016.
 */
@Named
@Service
public class CompareService implements CompareServiceImpl{
    @Inject
    private CompareDaoImpl compareDao;

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    private boolean isFlatCompare(UsersFildsEntity usersFildsEntity, Long FlatId){
        boolean searchFlat = false;
        return SearchFields.getFlatsList(usersFildsEntity).contains(FlatId);
    }

    @Override
    public boolean isFlatId(String userName, Long FlatId) {
        UsersFildsEntity usersFildsEntity = (UsersFildsEntity)compareDao.getCompare(userName);
        return isFlatCompare(usersFildsEntity, FlatId);
    }

    @Override
    public boolean setCompareFlat(String userName, Long FlatId) {
        UsersFildsEntity usersFildsEntity = (UsersFildsEntity)compareDao.getCompare(userName);
        if( usersFildsEntity==null){
            usersFildsEntity = new UsersFildsEntity();
            usersFildsEntity.setCompare(FlatId.toString());
            usersFildsEntity.setUsername(userName);
            saveService.inputObject(usersFildsEntity);
        }else if(!isFlatCompare(usersFildsEntity,FlatId)){
            if(usersFildsEntity.getCompare().length()>0) {
                usersFildsEntity.setCompare(usersFildsEntity.getCompare() + " " + FlatId.toString());
            } else{
                usersFildsEntity.setCompare(FlatId.toString());
            }
            saveService.inputObject(usersFildsEntity);
        }
        return true;
    }

    public boolean removeCompareFlat(String userName, Long FlatId) {
        UsersFildsEntity usersFildsEntity = (UsersFildsEntity)compareDao.getCompare(userName);
        if( usersFildsEntity!=null && isFlatCompare(usersFildsEntity,FlatId)){
            if(usersFildsEntity.getCompare().contains(" ")) {
                String[] fields = usersFildsEntity.getCompare().split(" ");
                    if(fields[0].equals(FlatId.toString())) {
                        usersFildsEntity.setCompare(usersFildsEntity.getCompare().replace(FlatId.toString() + " ", ""));
                    }
                    else{
                        usersFildsEntity.setCompare(usersFildsEntity.getCompare().replace(" " + FlatId.toString(), ""));
                    }
            } else if(usersFildsEntity.getCompare().length()>0){
                usersFildsEntity.setCompare("");
            }
            saveService.inputObject(usersFildsEntity);
        }
        return true;
    }

    public void setCompareDao(CompareDaoImpl compareDao) {
        this.compareDao = compareDao;
    }

    public void setSaveService(SaveOrUpdateObjectInputServiceImpl saveService) {
        this.saveService = saveService;
    }
}
