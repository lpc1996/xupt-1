package com.entity;

import java.sql.Date;
import java.util.Objects;

@javax.persistence.Entity
@javax.persistence.Table(name = "offering_courses", schema = "xupt")

/**
 * @author 濃霧-遠方
 * @date 2020/07/16
 */
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfferingCoursesEntity that = (OfferingCoursesEntity) o;

        if (maxNum != that.maxNum) {
            return false;
        }
        if (!Objects.equals(id, that.id)) {
            return false;
        }
        if (!Objects.equals(courseId, that.courseId)) {
            return false;
        }
        if (!Objects.equals(teacherId, that.teacherId)) {
            return false;
        }
        if (!Objects.equals(begin, that.begin)) {
            return false;
        }
        if (!Objects.equals(schoolYearId, that.schoolYearId)) {
            return false;
        }
        if (!Objects.equals(schoolTremId, that.schoolTremId)) {
            return false;
        }
        return Objects.equals(semesterId, that.semesterId);
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
