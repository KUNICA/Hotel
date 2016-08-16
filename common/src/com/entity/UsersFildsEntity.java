package com.entity;

import javax.persistence.*;

/**
 * Created by user on 07.08.2016.
 */
@Entity
@Table(name = "users_filds", schema = "hotel_work")
public class UsersFildsEntity {
    private Long id;
    private String username;
    private String compare;

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
    @Column(name = "compare", nullable = true, length = 225)
    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersFildsEntity that = (UsersFildsEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (compare != null ? !compare.equals(that.compare) : that.compare != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (compare != null ? compare.hashCode() : 0);
        return result;
    }
}
