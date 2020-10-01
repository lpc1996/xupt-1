package com.entity;

import java.sql.Timestamp;

@javax.persistence.Entity
@javax.persistence.Table(name = "result", schema = "xupt")
public class ResultEntity {
    private int id;
    private String studentId;
    private String courseId;
    private double result;
    private Timestamp time;
    private String teacherId;

    @javax.persistence.Id
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
    @javax.persistence.Column(name = "result")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultEntity that = (ResultEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.result, result) != 0) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1;
        long temp;
        result1 = id;
        result1 = 31 * result1 + (studentId != null ? studentId.hashCode() : 0);
        result1 = 31 * result1 + (courseId != null ? courseId.hashCode() : 0);
        temp = Double.doubleToLongBits(result);
        result1 = 31 * result1 + (int) (temp ^ (temp >>> 32));
        result1 = 31 * result1 + (time != null ? time.hashCode() : 0);
        result1 = 31 * result1 + (teacherId != null ? teacherId.hashCode() : 0);
        return result1;
    }
}
