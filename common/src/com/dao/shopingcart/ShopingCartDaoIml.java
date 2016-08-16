package com.dao.shopingcart;

import java.util.List;

/**
 * Created by user on 06.08.2016.
 */
public interface ShopingCartDaoIml {
     Object getShoppingCart(String userName,long flatId);
     boolean isFlatCheck(long flatId);
     List getSumShoppingCart(String userName);
     List getShoppingCarts(String userName);
}
