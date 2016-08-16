package com.services.account;

import com.entity.PersonalDataEntity;

/**
 * Created by user on 03.08.2016.
 */
public interface IPersonalDataService {
    PersonalDataEntity getPersonalData(String userName);
}
