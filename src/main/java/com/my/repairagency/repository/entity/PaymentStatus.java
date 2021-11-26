package com.my.repairagency.repository.entity;

public enum PaymentStatus {
    WAITING_FOR_PAYMENT("waiting for payment"),
    PAID("paid"),
    CANCELED("canceled");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public PaymentStatus getPaymentStatus(String status){
        for (PaymentStatus ps : PaymentStatus.values()) {
            if (ps.status.equals(status))
                return ps;
        }
        return null;
    }
}
