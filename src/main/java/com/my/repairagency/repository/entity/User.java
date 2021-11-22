package com.my.repairagency.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User entity
 */
public class User implements Serializable {
    private int id;
    private int roleId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private BigDecimal account;

    public User() {
    }

    public User(int id, int roleId, String name, String surname, String email, String password, BigDecimal account) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.account = account;
    }

    public User(int roleId, String name, String surname, String email, String password) {
        this.roleId = roleId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", account=" + account +
                '}';
    }
}
