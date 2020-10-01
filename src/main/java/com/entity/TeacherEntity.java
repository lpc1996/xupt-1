package com.entity;

import java.sql.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "teacher", schema = "xupt")
public class TeacherEntity {
    private String id;
    private String college;
    private String department;
    private String level;
    private String education;
    private Date year;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "college")
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "level",columnDefinition = "enum('助教','讲师','副教授','教授')")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "Education",columnDefinition = "enum('专科','本科','硕士','博士')")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "year")
    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (college != null ? !college.equals(that.college) : that.college != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (college != null ? college.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
