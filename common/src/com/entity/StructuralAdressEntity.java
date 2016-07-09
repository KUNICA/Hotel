package com.entity;

import javax.persistence.*;

/**
 * Created by user on 30.06.2016.
 */
@Entity
@Table(name = "structural_adress", schema = "hotel_work", catalog = "")
@IdClass(StructuralAdressEntityPK.class)
public class StructuralAdressEntity {
    private int id;
    private Integer operIn;
    private Integer operOut;
    private String act;
    private int nodeNumber;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "oper_in", nullable = true)
    public Integer getOperIn() {
        return operIn;
    }

    public void setOperIn(Integer operIn) {
        this.operIn = operIn;
    }

    @Basic
    @Column(name = "oper_out", nullable = true)
    public Integer getOperOut() {
        return operOut;
    }

    public void setOperOut(Integer operOut) {
        this.operOut = operOut;
    }

    @Basic
    @Column(name = "act", nullable = true, length = 1)
    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    @Id
    @Column(name = "node_number", nullable = false)
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

        StructuralAdressEntity that = (StructuralAdressEntity) o;

        if (id != that.id) return false;
        if (nodeNumber != that.nodeNumber) return false;
        if (operIn != null ? !operIn.equals(that.operIn) : that.operIn != null) return false;
        if (operOut != null ? !operOut.equals(that.operOut) : that.operOut != null) return false;
        if (act != null ? !act.equals(that.act) : that.act != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (operIn != null ? operIn.hashCode() : 0);
        result = 31 * result + (operOut != null ? operOut.hashCode() : 0);
        result = 31 * result + (act != null ? act.hashCode() : 0);
        result = 31 * result + nodeNumber;
        return result;
    }
}
