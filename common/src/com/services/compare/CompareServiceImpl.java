package com.services.compare;

/**
 * Created by user on 07.08.2016.
 */
public interface CompareServiceImpl {
    boolean isFlatId(String userName,Long FlatId);
    boolean setCompareFlat(String userName,Long FlatId);
    boolean removeCompareFlat(String userName, Long FlatId);
}
