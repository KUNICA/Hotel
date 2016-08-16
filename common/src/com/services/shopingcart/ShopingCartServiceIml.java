package com.services.shopingcart;

import com.dataweb.Product;

import java.util.List;

/**
 * Created by user on 06.08.2016.
 */
public interface ShopingCartServiceIml {
    Object getShoppingCart(String userName,long flatId);
    Object checkProduct(Product product, String Username);
    boolean isFlatCheck(long flatId);
    List getSumShoppingCart(String userName);
    List getShoppingCarts(String userName);
}
