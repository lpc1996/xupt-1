package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login", schema = "xupt")

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class LoginEntity {
    private String uId;
    private String uName;
    private Integer uLimit;
    private String uPass;

    @Id
    @Column(name = "u_id")
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "u_name")
    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Basic
    @Column(name = "u_limit",columnDefinition = "enum('1','2','3','4','5','6','7','8','9')")
    public Integer getuLimit() {
        return uLimit;
    }

    public void setuLimit(Integer uLimit) {
        this.uLimit = uLimit;
    }

    @Basic
    @Column(name = "u_pass")
    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoginEntity that = (LoginEntity) o;

        if (!Objects.equals(uId, that.uId)) {
            return false;
        }
        if (!Objects.equals(uName, that.uName)) {
            return false;
        }
        if (!Objects.equals(uLimit, that.uLimit)) {
            return false;
        }
        return Objects.equals(uPass, that.uPass);
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
