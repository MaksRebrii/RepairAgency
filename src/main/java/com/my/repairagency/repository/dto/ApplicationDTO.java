package com.my.repairagency.repository.dto;

import com.my.repairagency.repository.entity.Application;

import java.io.Serializable;
import java.math.BigDecimal;

public class ApplicationDTO implements Serializable {
    private int id;
    private UserWithRoleDTO client;
    private UserWithRoleDTO master;
    private String description;
    private BigDecimal price;
    private String review;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Application application, UserWithRoleDTO client, UserWithRoleDTO master) {
        this.id = application.getId();
        this.client = client;
        this.master = master;
        this.description = application.getDescription();
        this.price = application.getPrice();
        this.review = application.getReview();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserWithRoleDTO getClient() {
        return client;
    }

    public void setClient(UserWithRoleDTO client) {
        this.client = client;
    }

    public UserWithRoleDTO getMaster() {
        return master;
    }

    public void setMaster(UserWithRoleDTO master) {
        this.master = master;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "id=" + id +
                ", client=" + client +
                ", master=" + master +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", review='" + review + '\'' +
                '}';
    }
}
