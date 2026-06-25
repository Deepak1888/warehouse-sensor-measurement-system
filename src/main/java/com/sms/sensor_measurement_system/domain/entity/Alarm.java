package com.sms.sensor_measurement_system.domain.entity;


import java.time.Instant;

public class Alarm {

    private final String message;

    private final Instant createdAt;

    public Alarm(String message) {
        this.message = message;
        this.createdAt = Instant.now();
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}