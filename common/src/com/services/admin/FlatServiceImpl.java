package com.services.admin;

import java.util.List;

/**
 * Created by user on 10.08.2016.
 */
public interface FlatServiceImpl {
    List getFlats(String userName);
    boolean removeFlat(long flatId,String userName);
}
