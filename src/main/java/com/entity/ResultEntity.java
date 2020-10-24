package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "result", schema = "xupt")
public class ResultEntity {
    private int id;
    private String studentId;
    private String courseId;
    private String oc;
    private double result;
    private Date time;
    private String teacherId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "course_id")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "oc")
    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }

    @Basic
    @Column(name = "result")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "teacher_id")
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultEntity that = (ResultEntity) o;

        if (id != that.id) {
            return false;
        }
        if (Double.compare(that.result, result) != 0) {
            return false;
        }
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) {
            return false;
        }
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) {
            return false;
        }
        if (oc != null ? !oc.equals(that.oc) : that.oc != null) {
            return false;
        }
        if (time != null ? !time.equals(that.time) : that.time != null) {
            return false;
        }
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result1;
        long temp;
        result1 = id;
        result1 = 31 * result1 + (studentId != null ? studentId.hashCode() : 0);
        result1 = 31 * result1 + (courseId != null ? courseId.hashCode() : 0);
        result1 = 31 * result1 + (oc != null ? oc.hashCode() : 0);
        temp = Double.doubleToLongBits(result);
        result1 = 31 * result1 + (int) (temp ^ (temp >>> 32));
        result1 = 31 * result1 + (time != null ? time.hashCode() : 0);
        result1 = 31 * result1 + (teacherId != null ? teacherId.hashCode() : 0);
        return result1;
    }
}
