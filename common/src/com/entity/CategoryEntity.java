package com.entity;

import javax.persistence.*;

/**
 * Created by user on 30.06.2016.
 */
@Entity
@Table(name = "category", schema = "hotel_work", catalog = "")
public class CategoryEntity {
    private int id;
    private Integer typeCatecory;
    private String fullName;
    private String prefix;
    private String act;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_catecory", nullable = true)
    public Integer getTypeCatecory() {
        return typeCatecory;
    }

    public void setTypeCatecory(Integer typeCatecory) {
        this.typeCatecory = typeCatecory;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = 20)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "prefix", nullable = true, length = 20)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Basic
    @Column(name = "act", nullable = true, length = 1)
    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != that.id) return false;
        if (typeCatecory != null ? !typeCatecory.equals(that.typeCatecory) : that.typeCatecory != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
        if (act != null ? !act.equals(that.act) : that.act != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeCatecory != null ? typeCatecory.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        result = 31 * result + (act != null ? act.hashCode() : 0);
        return result;
    }
}
