package com.services.offer;

import com.dao.flats.FlatDaoImpl;
import com.dao.offer.OfferDaoImpl;
import com.entity.*;
import com.services.shopingcart.ShopingCartServiceIml;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created by user on 12.08.2016.
 */
@Named
public class OfferService implements OfferServiceImpl{

    @Inject
    private ShopingCartServiceIml shopingCartServiceIml;

    @Inject
    private FlatDaoImpl flatDaoImpl;

    @Inject
    private OfferDaoImpl OfferDao;

    @Override
    public Object create(String userName) {
        List list = shopingCartServiceIml.getShoppingCarts(userName);
        OfferEntity offer = new OfferEntity();
        Set<ShoppingCartEntity> shoppingCar = new HashSet<ShoppingCartEntity>();
        Double sumOffer = 0D;
        for (Object iter :list) {
            ShoppingCartEntity product = (ShoppingCartEntity)iter;
            product.setActual(1L);
            shoppingCar.add(product);
            FlatsEntity flat = (FlatsEntity)flatDaoImpl.getFlat(product.getFlatId());
            sumOffer+=flat.getPrice();
        }
        offer.setShoppingCart(shoppingCar);
        offer.setOrderPrice(sumOffer);
        OperationsEntity operationIn = new OperationsEntity();
        operationIn.setDateOper(new Date());
        operationIn.setTypeOper(OperationType.OPERATION_IN);
        operationIn.setUserName(userName);
        offer.setOperationIn(operationIn);

        return offer;
    }

    @Override
    public Object getOffer(String userName) {
        return OfferDao.getOffer(userName);
    }

    public List getFlats(Set shopingCartlist){
        ArrayList<FlatsEntity> flats = new ArrayList<FlatsEntity>();
        for (Object iter :shopingCartlist) {
            ShoppingCartEntity product = (ShoppingCartEntity)iter;
            FlatsEntity flat = (FlatsEntity)flatDaoImpl.getFlat(product.getFlatId());
            flats.add(flat);
        }
        return flats;
    }
}
