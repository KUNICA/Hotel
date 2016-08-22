package com.services.admin;

import com.entity.*;

import javax.inject.Named;
import java.util.Date;

/**
 * Created by user on 10.08.2016.
 */
@Named
public class RemoveService implements RemoveServiceImpl {

    @Override
    public boolean remove(FlatsEntity flat,String userName) {
        for (ImagesEntity image : flat.getPhotos()) {
            image.setOperationOut(getOperationOut(userName));
        }
        for (VideoEntity video : flat.getVideos()) {
            video.setOperationOut(getOperationOut(userName));
        }
        flat.setOperationOut(getOperationOut(userName));
        return true;
    }

    private OperationsEntity getOperationOut(String userName){
        OperationsEntity operationOut = new OperationsEntity();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        return operationOut;
    }
}
