package com.services.offer;


import java.util.List;
import java.util.Set;

/**
 * Created by user on 12.08.2016.
 */
public interface OfferServiceImpl {
    Object create(String userName);
    Object getOffer(String userName);
    List getFlats(Set shopingCartlist);
}
