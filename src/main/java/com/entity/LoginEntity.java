package com.entity;

import javax.persistence.Column;

@javax.persistence.Entity
@javax.persistence.Table(name = "login", schema = "xupt")
public class LoginEntity {
    private String uId;
    private String uName;
    private Integer uLimit;
    private String uPass;

    @javax.persistence.Id
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
    @Column(name = "u_limit",columnDefinition = "enum('1','2','3','4','5','6','7','8','9')")
    public Integer getuLimit() {
        return uLimit;
    }

    public void setuLimit(Integer uLimit) {
        this.uLimit = uLimit;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "u_pass")
    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginEntity that = (LoginEntity) o;

        if (uId != null ? !uId.equals(that.uId) : that.uId != null) return false;
        if (uName != null ? !uName.equals(that.uName) : that.uName != null) return false;
        if (uLimit != null ? !uLimit.equals(that.uLimit) : that.uLimit != null) return false;
        if (uPass != null ? !uPass.equals(that.uPass) : that.uPass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uId != null ? uId.hashCode() : 0;
        result = 31 * result + (uName != null ? uName.hashCode() : 0);
        result = 31 * result + (uLimit != null ? uLimit.hashCode() : 0);
        result = 31 * result + (uPass != null ? uPass.hashCode() : 0);
        return result;
    }
}
