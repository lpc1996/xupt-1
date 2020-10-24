package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@javax.persistence.Entity
@javax.persistence.Table(name = "base_info", schema = "xupt")

public class BaseInfoEntity {
    private int id;
    private String uId;
    private String uName;
    private String formarName;
    private String sex;
    private int age;
    private String nativePlace;
    private String idcardType;
    private String idcardNum;
    private String uType;
    private String tel;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "u_id")
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "u_name")
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "formar_name")
    public String getFormarName() {
        return formarName;
    }

    public void setFormarName(String formarName) {
        this.formarName = formarName;
    }

    @javax.persistence.Basic
    @Column(name = "sex",columnDefinition = "enum('男','女')")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "native_place")
    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    @javax.persistence.Basic
    @Column(name = "IDCARD_type",columnDefinition = "enum('居民身份证','中华人民共和国护照')")
    public String getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(String idcardType) {
        this.idcardType = idcardType;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "IDCARD_num")
    public String getIdcardNum() {
        return idcardNum;
    }

    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum;
    }

    @javax.persistence.Basic
    @Column(name = "u_type",columnDefinition = "enum('student','teacher','admin')")
    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseInfoEntity that = (BaseInfoEntity) o;

        if (id != that.id) {
            return false;
        }
        if (age != that.age) {
            return false;
        }
        if (uId != null ? !uId.equals(that.uId) : that.uId != null) {
            return false;
        }
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) {
            return false;
        }
        if (formarName != null ? !formarName.equals(that.formarName) : that.formarName != null) {
            return false;
        }
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) {
            return false;
        }
        if (nativePlace != null ? !nativePlace.equals(that.nativePlace) : that.nativePlace != null) {
            return false;
        }
        if (idcardType != null ? !idcardType.equals(that.idcardType) : that.idcardType != null) {
            return false;
        }
        if (idcardNum != null ? !idcardNum.equals(that.idcardNum) : that.idcardNum != null) {
            return false;
        }
        if (uType != null ? !uType.equals(that.uType) : that.uType != null) {
            return false;
        }
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (formarName != null ? formarName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (nativePlace != null ? nativePlace.hashCode() : 0);
        result = 31 * result + (idcardType != null ? idcardType.hashCode() : 0);
        result = 31 * result + (idcardNum != null ? idcardNum.hashCode() : 0);
        result = 31 * result + (uType != null ? uType.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        return result;
    }
}
