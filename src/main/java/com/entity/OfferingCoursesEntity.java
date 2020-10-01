package com.entity;

import java.sql.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "offering_courses", schema = "xupt")
public class OfferingCoursesEntity {
    private String id;
    private String courseId;
    private String teacherId;
    private Date begin;
    private String schoolYearId;
    private String schoolTremId;
    private String semesterId;
    private int maxNum;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @javax.persistence.Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "begin")
    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
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
    @javax.persistence.Column(name = "semester_id")
    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "max_num")
    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferingCoursesEntity that = (OfferingCoursesEntity) o;

        if (maxNum != that.maxNum) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (begin != null ? !begin.equals(that.begin) : that.begin != null) return false;
        if (schoolYearId != null ? !schoolYearId.equals(that.schoolYearId) : that.schoolYearId != null) return false;
        if (schoolTremId != null ? !schoolTremId.equals(that.schoolTremId) : that.schoolTremId != null) return false;
        if (semesterId != null ? !semesterId.equals(that.semesterId) : that.semesterId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (schoolYearId != null ? schoolYearId.hashCode() : 0);
        result = 31 * result + (schoolTremId != null ? schoolTremId.hashCode() : 0);
        result = 31 * result + (semesterId != null ? semesterId.hashCode() : 0);
        result = 31 * result + maxNum;
        return result;
    }
}
