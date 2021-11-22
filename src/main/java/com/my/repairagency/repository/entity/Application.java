package com.my.repairagency.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Application entity
 */
public class Application implements Serializable {
    private int id;
    private int clientId;
    private int masterId;
    private String description;
    private BigDecimal price;
    private String review;

    public Application() {
    }

    public Application(int applicationId, int clientId, int masterId, String description, BigDecimal price, String applicationReview) {
        this.id = applicationId;
        this.clientId = clientId;
        this.masterId = masterId;
        this.description = description;
        this.price = price;
        this.review = applicationReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
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
        return "Application{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", masterId=" + masterId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", review='" + review + '\'' +
                '}';
    }
}
