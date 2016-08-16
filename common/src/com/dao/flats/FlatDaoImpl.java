package com.dao.flats;

import java.util.List;

/**
 * Created by user on 08.08.2016.
 */
public interface FlatDaoImpl {
    List getFlats(List flatsIdList);
    Object getFlat(Long flatId);
    List getFlats(String userName);
}
