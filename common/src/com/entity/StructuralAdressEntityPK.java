package com.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by user on 30.06.2016.
 */
public class StructuralAdressEntityPK implements Serializable {
    private int id;
    private int nodeNumber;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "node_number", nullable = false)
    @Id
    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StructuralAdressEntityPK that = (StructuralAdressEntityPK) o;

        if (id != that.id) return false;
        if (nodeNumber != that.nodeNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nodeNumber;
        return result;
    }
}
