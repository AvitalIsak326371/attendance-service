package com.example.attendance.Entity;

import com.example.attendance.Entity.status;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class Student {
    @Id
    private int student_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private com.example.attendance.Entity.status status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Student(int student_id, String first_name, String last_name, String email, String phone, status status) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.created_at=LocalDateTime.now();
        this.updated_at=LocalDateTime.now();
    }

    public Student() {

    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public status  getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }
}
