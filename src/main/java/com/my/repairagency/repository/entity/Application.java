package com.my.repairagency.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Application entity
 */
public class Application implements Serializable {
    private int id;
    private int clientId;
    private int masterId;
    private Timestamp date;
    private CompletionStatus completionStatus;
    private PaymentStatus paymentStatus;
    private String description;
    private BigDecimal price;
    private String review;

    public Application() {
    }

    public Application(int id, int clientId, int masterId, Timestamp date,
                       CompletionStatus completionStatus, PaymentStatus paymentStatus,
                       String description, BigDecimal price, String review) {
        this.id = id;
        this.clientId = clientId;
        this.masterId = masterId;
        this.date = date;
        this.completionStatus = completionStatus;
        this.paymentStatus = paymentStatus;
        this.description = description;
        this.price = price;
        this.review = review;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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
                ", date=" + date +
                ", completionStatus='" + completionStatus + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", review='" + review + '\'' +
                '}';
    }
}
