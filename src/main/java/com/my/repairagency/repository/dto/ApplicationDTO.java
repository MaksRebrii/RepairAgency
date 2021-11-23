package com.my.repairagency.repository.dto;

import com.my.repairagency.repository.entity.Application;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ApplicationDTO implements Serializable {



    private int id;
    private UserWithRoleDTO client;
    private UserWithRoleDTO master;
    private Timestamp date;
    private String completionStatus;
    private String paymentStatus;
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
        this.date = application.getDate();
        this.completionStatus = application.getCompletionStatus();
        this.paymentStatus = application.getPaymentStatus();
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "id=" + id +
                ", client=" + client +
                ", master=" + master +
                ", date=" + date +
                ", completionStatus='" + completionStatus + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", review='" + review + '\'' +
                '}';
    }
}
