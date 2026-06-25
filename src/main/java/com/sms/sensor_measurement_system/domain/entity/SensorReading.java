package com.sms.sensor_measurement_system.domain.entity;


import com.sms.sensor_measurement_system.domain.valueobject.MeasurementValue;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;

import java.time.Instant;

public class SensorReading {

    private final SensorId sensorId;

    private final MeasurementValue value;

    private final Instant timestamp;

    public SensorReading(SensorId sensorId,
                         MeasurementValue value,
                         Instant timestamp) {

        this.sensorId = sensorId;
        this.value = value;
        this.timestamp = timestamp;

    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public MeasurementValue getValue() {
        return value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}