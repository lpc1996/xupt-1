package com.entity;

import java.util.Objects;

@javax.persistence.Entity
@javax.persistence.Table(name = "college", schema = "xupt")

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
public class CollegeEntity {
    private String id;
    private String name;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CollegeEntity that = (CollegeEntity) o;

        if (!Objects.equals(id, that.id)) {
            return false;
        }
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
