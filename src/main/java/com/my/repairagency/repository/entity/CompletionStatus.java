package com.my.repairagency.repository.entity;

public enum CompletionStatus {
    NOT_STARTED("not started"),
    IN_WORK("in work"),
    DONE("done");

    private final String status;

    CompletionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
