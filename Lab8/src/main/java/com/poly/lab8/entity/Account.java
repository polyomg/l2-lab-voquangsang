package com.poly.lab8.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
@Entity
@Table(name = "Accounts") // trùng với DB
@Data
public class Account implements Serializable {
    @Id
    private String username;
    private String password;
    private String role; // USER, ADMIN, EMPLOYEE, FOUNDER
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String status;

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }
}