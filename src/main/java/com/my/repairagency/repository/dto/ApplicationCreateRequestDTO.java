package com.my.repairagency.repository.dto;

import java.sql.Timestamp;

public class ApplicationCreateRequestDTO {

    private int clientId;
    private String description;
    private Timestamp timeCreation;

    public ApplicationCreateRequestDTO(int clientId, String description, Timestamp timeCreation) {
        this.clientId = clientId;
        this.description = description;
        this.timeCreation = timeCreation;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
    }

    @Override
    public String toString() {
        return "ApplicationCreateRequestDTO{" +
                "clientId=" + clientId +
                ", description='" + description + '\'' +
                ", timeCreation=" + timeCreation +
                '}';
    }
}
