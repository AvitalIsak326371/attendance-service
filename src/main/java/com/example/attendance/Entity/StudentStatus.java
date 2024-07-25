package com.example.attendance.Entity;

public class StudentStatus
{
    public   String studentId;
    public AttendanceStatus status;

    @Override
    public String toString() {
        return "StudentStatus{" +
                "studentId='" + studentId + '\'' +
                ", status=" + status +
                '}';
    }


    public String getStudent_id() {
        return studentId;
    }

    public void setStudent_id(String student_id) {
        this.studentId = student_id;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }



}
