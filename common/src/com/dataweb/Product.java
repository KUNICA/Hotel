package com.dataweb;

import com.entity.ShoppingCartEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by user on 06.08.2016.
 */
public class Product implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("flatId")
    private Long flatId;
    @JsonProperty("totalAmount")
    private Double totalAmount;
    @JsonProperty("actual")
    private Long actual;
    @JsonProperty("check")
    private Boolean check;

    public Product(){

    }

    public Product(ShoppingCartEntity shoppingCartEntity,String userName) {
        this.id = shoppingCartEntity.getId();
        this.username = userName;
        this.flatId = shoppingCartEntity.getFlatId();
        this.totalAmount = shoppingCartEntity.getTotalAmount();
        this.actual = shoppingCartEntity.getActual();
        this.check = shoppingCartEntity.getCheck();
    }
    public Product(String userName) {
        this.username = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Long getFlatId() {
        return flatId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Long getActual() {
        return actual;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setActual(Long actual) {
        this.actual = actual;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
