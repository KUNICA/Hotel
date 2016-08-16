package com.services.shopingcart;

import com.dao.shopingcart.ShopingCartDaoIml;
import com.dataweb.Product;
import com.entity.ShoppingCartEntity;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 06.08.2016.
 */
@Named
@Service
public class ShopingCartService implements ShopingCartServiceIml {

    @Inject
    private ShopingCartDaoIml shopingCartDao;

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    @Override
    public Object getShoppingCart(String userName,long flatId) {
        return shopingCartDao.getShoppingCart(userName,flatId);
    }

    @Override
    public Object checkProduct(Product product,String Username) {
        ShoppingCartEntity shoppingCartEntity  = new ShoppingCartEntity();
        shoppingCartEntity.setActual(product.getActual());
        shoppingCartEntity.setCheck(product.getCheck());
        shoppingCartEntity.setFlatId(product.getFlatId());
        shoppingCartEntity.setId(product.getId());
        shoppingCartEntity.setUsername(product.getUsername());
        return saveService.inputObject(shoppingCartEntity);
    }

    @Override
    public boolean isFlatCheck(long flatId) {
        return shopingCartDao.isFlatCheck(flatId);
    }

    public List getSumShoppingCart(String userName){
       return  shopingCartDao.getSumShoppingCart(userName);
    }

    public List getShoppingCarts(String userName){
        return  shopingCartDao.getShoppingCarts(userName);
    }
}
