package com.sms.sensor_measurement_system.domain.valueobject;


public record SensorId(String value) {

    public static SensorId of(String value) {
        return new SensorId(value);
    }
}