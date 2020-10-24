package com.entity;

@javax.persistence.Entity
@javax.persistence.Table(name = "team", schema = "xupt")
public class TeamEntity {
    private String id;
    private String name;
    private int number;
    private String collegeId;
    private String departmentId;
    private String majorId;
    private String semester;

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
    @javax.persistence.Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    @javax.persistence.Basic
    @javax.persistence.Column(name = "major_id")
    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "semester")
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeamEntity that = (TeamEntity) o;

        if (number != that.number) {
            return false;
        }
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
        if (majorId != null ? !majorId.equals(that.majorId) : that.majorId != null) {
            return false;
        }
        if (semester != null ? !semester.equals(that.semester) : that.semester != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (collegeId != null ? collegeId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (majorId != null ? majorId.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}
