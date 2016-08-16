package com.dao.account;

import com.entity.PersonalDataEntity;

/**
 * Created by user on 03.08.2016.
 */
public interface IPersonalDataDao {
    PersonalDataEntity getPersonalData(String userName);
}
