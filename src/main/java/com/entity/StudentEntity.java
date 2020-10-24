package com.entity;

import java.sql.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "student", schema = "xupt")
public class StudentEntity {
    private String id;
    private Date year;
    private String college;
    private String department;
    private String major;
    private String grade;
    private String clazz;
    private String cultureLevel;
    private String studentType;
    private String education;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "year")
    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
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
    @javax.persistence.Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "class")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "culture_level",columnDefinition = "enum('专科','本科','硕士','博士')")
    public String getCultureLevel() {
        return cultureLevel;
    }

    public void setCultureLevel(String cultureLevel) {
        this.cultureLevel = cultureLevel;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "student_type",columnDefinition = "enum('普通专科生','普通本科生','硕士研究生','博士生')")
    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "Education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentEntity that = (StudentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (year != null ? !year.equals(that.year) : that.year != null) {
            return false;
        }
        if (college != null ? !college.equals(that.college) : that.college != null) {
            return false;
        }
        if (department != null ? !department.equals(that.department) : that.department != null) {
            return false;
        }
        if (major != null ? !major.equals(that.major) : that.major != null) {
            return false;
        }
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) {
            return false;
        }
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) {
            return false;
        }
        if (cultureLevel != null ? !cultureLevel.equals(that.cultureLevel) : that.cultureLevel != null) {
            return false;
        }
        if (studentType != null ? !studentType.equals(that.studentType) : that.studentType != null) {
            return false;
        }
        if (education != null ? !education.equals(that.education) : that.education != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (college != null ? college.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (cultureLevel != null ? cultureLevel.hashCode() : 0);
        result = 31 * result + (studentType != null ? studentType.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        return result;
    }
}
