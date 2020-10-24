package com.entity;

@javax.persistence.Entity
@javax.persistence.Table(name = "major", schema = "xupt")
public class MajorEntity {
    private String id;
    private String name;
    private String collegeId;
    private String departmentId;

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

    @javax.persistence.Basic
    @javax.persistence.Column(name = "college_id")
    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "department_id")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MajorEntity that = (MajorEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (collegeId != null ? !collegeId.equals(that.collegeId) : that.collegeId != null) {
            return false;
        }
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (collegeId != null ? collegeId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        return result;
    }
}
