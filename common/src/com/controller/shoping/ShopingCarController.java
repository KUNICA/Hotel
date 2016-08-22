package com.controller.shoping;

import com.dataweb.Product;
import com.entity.ShoppingCartEntity;
import com.services.shopingcart.ShopingCartServiceIml;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by user on 06.08.2016.
 */
@Controller
@RequestMapping("/shoping")
public class ShopingCarController {

    @Inject
    private ShopingCartServiceIml shopingCartService;

    @RequestMapping(value = "/cart/{flatId}", method = RequestMethod.POST)
    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    public @ResponseBody
    Product getCartFlat(@PathVariable("flatId") long flatId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCartEntity shoppingCartEntity = (ShoppingCartEntity)shopingCartService.getShoppingCart(userName,flatId);
        return shoppingCartEntity!=null ? new Product(shoppingCartEntity,userName): new Product(userName) ;
    }

    @RequestMapping(value = "/save",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    public @ResponseBody
    Product setCart(@RequestBody Product product) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCartEntity shoppingCartEntity = (ShoppingCartEntity) shopingCartService.checkProduct(product,userName);
        return shoppingCartEntity!=null ? new Product(shoppingCartEntity,userName): new Product(userName) ;
    }

    @RequestMapping(value = "/ischeck/{flatId}", method = RequestMethod.GET)
    public @ResponseBody Boolean isCheck(@PathVariable("flatId") long id) {
        return shopingCartService.isFlatCheck(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody List getProducts() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return shopingCartService.getSumShoppingCart(userName);
    }

}
