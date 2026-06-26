package com.sms.sensor_measurement_system.dto;

import java.time.Instant;


public record SensorReadingResponse(
        String sensorId,
        String sensorType,
        double value,
        Instant timestamp
) {
}