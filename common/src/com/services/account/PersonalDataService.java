package com.services.account;

import com.dao.account.IPersonalDataDao;
import com.entity.PersonalDataEntity;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by user on 03.08.2016.
 */
@Named
@Service
public class PersonalDataService implements IPersonalDataService {
    @Inject
    protected IPersonalDataDao personalDataDao;

    @Override
    public PersonalDataEntity getPersonalData(String userName) {
        return personalDataDao.getPersonalData(userName);
    }


}
