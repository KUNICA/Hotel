package com.services.alert;

import com.entity.OfferEntity;

/**
 * Created by user on 13.08.2016.
 */
public interface AlertServiceImpl {
    void sendAlert(final OfferEntity offer);
}
