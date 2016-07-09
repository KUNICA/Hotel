package com.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by user on 30.06.2016.
 */
@Entity
@Table(name = "operations", schema = "hotel_work", catalog = "")
public class OperationsEntity {
    private int id;
    private int typeOper;
    private Date dateOper;
    private String userName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_oper", nullable = false)
    public int getTypeOper() {
        return typeOper;
    }

    public void setTypeOper(int typeOper) {
        this.typeOper = typeOper;
    }

    @Basic
    @Column(name = "date_oper", nullable = false)
    public Date getDateOper() {
        return dateOper;
    }

    public void setDateOper(Date dateOper) {
        this.dateOper = dateOper;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationsEntity that = (OperationsEntity) o;

        if (id != that.id) return false;
        if (typeOper != that.typeOper) return false;
        if (dateOper != null ? !dateOper.equals(that.dateOper) : that.dateOper != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + typeOper;
        result = 31 * result + (dateOper != null ? dateOper.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
