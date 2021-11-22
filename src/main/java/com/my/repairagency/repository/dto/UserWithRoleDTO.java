package com.my.repairagency.repository.dto;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class contains user with his role
 */
public class UserWithRoleDTO implements Serializable {
    private int id;
    private String role;
    private String name;
    private String surname;
    private String email;
    private String password;
    private BigDecimal account;

    public UserWithRoleDTO() {
    }

    public UserWithRoleDTO(int userId, String userRole, String userName, String surname, String userEmail, String password, BigDecimal account) {
        this.id = userId;
        this.role = userRole;
        this.name = userName;
        this.surname = surname;
        this.email = userEmail;
        this.password = password;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account +
                '}';
    }
}
