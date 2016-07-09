package com.entity;

import javax.persistence.*;

/**
 * Created by user on 30.06.2016.
 */
@Entity
@Table(name = "adress", schema = "hotel_work", catalog = "")
public class AdressEntity {
    private int id;
    private Integer buildingNum;
    private String buildingIndex;
    private Integer сorpNum;
    private String corpIndex;
    private Integer flatNum;
    private String flatIndex;
    private String remark;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "building_num", nullable = true)
    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    @Basic
    @Column(name = "building_index", nullable = true, length = 100)
    public String getBuildingIndex() {
        return buildingIndex;
    }

    public void setBuildingIndex(String buildingIndex) {
        this.buildingIndex = buildingIndex;
    }

    @Basic
    @Column(name = "сorp_num", nullable = true)
    public Integer getСorpNum() {
        return сorpNum;
    }

    public void setСorpNum(Integer сorpNum) {
        this.сorpNum = сorpNum;
    }

    @Basic
    @Column(name = "corp_index", nullable = true, length = 100)
    public String getCorpIndex() {
        return corpIndex;
    }

    public void setCorpIndex(String corpIndex) {
        this.corpIndex = corpIndex;
    }

    @Basic
    @Column(name = "flatNum", nullable = true)
    public Integer getFlatNum() {
        return flatNum;
    }

    public void setFlatNum(Integer flatNum) {
        this.flatNum = flatNum;
    }

    @Basic
    @Column(name = "flatIndex", nullable = true, length = 100)
    public String getFlatIndex() {
        return flatIndex;
    }

    public void setFlatIndex(String flatIndex) {
        this.flatIndex = flatIndex;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 1000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdressEntity that = (AdressEntity) o;

        if (id != that.id) return false;
        if (buildingNum != null ? !buildingNum.equals(that.buildingNum) : that.buildingNum != null) return false;
        if (buildingIndex != null ? !buildingIndex.equals(that.buildingIndex) : that.buildingIndex != null)
            return false;
        if (сorpNum != null ? !сorpNum.equals(that.сorpNum) : that.сorpNum != null) return false;
        if (corpIndex != null ? !corpIndex.equals(that.corpIndex) : that.corpIndex != null) return false;
        if (flatNum != null ? !flatNum.equals(that.flatNum) : that.flatNum != null) return false;
        if (flatIndex != null ? !flatIndex.equals(that.flatIndex) : that.flatIndex != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (buildingNum != null ? buildingNum.hashCode() : 0);
        result = 31 * result + (buildingIndex != null ? buildingIndex.hashCode() : 0);
        result = 31 * result + (сorpNum != null ? сorpNum.hashCode() : 0);
        result = 31 * result + (corpIndex != null ? corpIndex.hashCode() : 0);
        result = 31 * result + (flatNum != null ? flatNum.hashCode() : 0);
        result = 31 * result + (flatIndex != null ? flatIndex.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
