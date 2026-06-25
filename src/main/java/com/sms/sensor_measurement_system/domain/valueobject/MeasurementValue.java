package com.sms.sensor_measurement_system.domain.valueobject;

public record MeasurementValue(double value) {

    public MeasurementValue {

        if (value < -100 || value > 200) {
            throw new IllegalArgumentException("Invalid measurement");
        }

    }

}