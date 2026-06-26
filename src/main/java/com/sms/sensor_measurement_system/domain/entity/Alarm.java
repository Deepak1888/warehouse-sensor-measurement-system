package com.sms.sensor_measurement_system.domain.entity;


import java.time.Instant;

public class Alarm {

    private final String sensorId;

    private final String sensorType;

    private final double value;

    private final String message;

    private final Instant createdAt;

    public Alarm(String sensorId,
                 String sensorType,
                 double value,
                 String message) {

        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.value = value;
        this.message = message;
        this.createdAt = Instant.now();

    }

    public String getSensorId() {
        return sensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public double getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}