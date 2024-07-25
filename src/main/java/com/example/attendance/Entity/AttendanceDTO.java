package com.example.attendance.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class AttendanceDTO
{


    @Id
    private Integer attendanceId;
    private String studentId;
    private int classId;
    private Date classDate;
    private AttendanceStatus status;
    private String markedById;


    public AttendanceDTO(String studentId, int classId, Date classDate, AttendanceStatus status, String markedById) {
        this.studentId = studentId;
        this.classId = classId;
        this.classDate = classDate;
        this.status = status;
        this.markedById = markedById;
    }

    public AttendanceDTO() {
    }
    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }
    public Integer getAttendanceId() {
        return attendanceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public String getMarkedById() {
        return markedById;
    }

    public void setMarkedById(String markedById) {
        this.markedById = markedById;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                ", classId=" + classId +
                ", classDate=" + classDate +
                ", status=" + status +
                ", markedById=" + markedById +
                '}';
    }


}
