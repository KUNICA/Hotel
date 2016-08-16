package com.entity;

import javax.persistence.*;

/**
 * Created by user on 06.08.2016.
 */
@Entity
@Table(name = "shopping_cart", schema = "hotel_work")

public class ShoppingCartEntity {
    private Long id;
    private String username;
    private Long flatId;
    private Double totalAmount;
    private Long actual;
    private Boolean check;
    private Long offerId;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 225)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "flat", nullable = true,columnDefinition = "bigint(10)")
    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    @Basic
    @Column(name = "total_amount", nullable = true, precision = 0)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "shopactual", nullable = true,columnDefinition = "bigint(10)")
    public Long getActual() {
        return actual;
    }

    public void setActual(Long actual) {
        this.actual = actual;
    }

    @Basic
    @Column(name = "shopcheck", nullable = false, length = 1)
    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    @Basic
    @Column(name = "offer_id", nullable = true,columnDefinition = "bigint(10)")
    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCartEntity that = (ShoppingCartEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (flatId != null ? !flatId.equals(that.flatId) : that.flatId != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (actual != null ? !actual.equals(that.actual) : that.actual != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (flatId != null ? flatId.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        return result;
    }
}
