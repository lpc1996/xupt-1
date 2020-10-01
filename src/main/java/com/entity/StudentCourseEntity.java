package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "student_course", schema = "xupt")
public class StudentCourseEntity {
    private int id;
    private String studentId;
    private String courseId;
    private String schoolYearId;
    private String schoolTremId;
    private Date time;

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
    @javax.persistence.Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "course_id")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "school_year_id")
    public String getSchoolYearId() {
        return schoolYearId;
    }

    public void setSchoolYearId(String schoolYearId) {
        this.schoolYearId = schoolYearId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "school_trem_id")
    public String getSchoolTremId() {
        return schoolTremId;
    }

    public void setSchoolTremId(String schoolTremId) {
        this.schoolTremId = schoolTremId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentCourseEntity that = (StudentCourseEntity) o;

        if (id != that.id) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (schoolYearId != null ? !schoolYearId.equals(that.schoolYearId) : that.schoolYearId != null) return false;
        if (schoolTremId != null ? !schoolTremId.equals(that.schoolTremId) : that.schoolTremId != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (schoolYearId != null ? schoolYearId.hashCode() : 0);
        result = 31 * result + (schoolTremId != null ? schoolTremId.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
