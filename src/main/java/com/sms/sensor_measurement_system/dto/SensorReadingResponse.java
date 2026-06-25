package com.sms.sensor_measurement_system.dto;

import java.time.Instant;

public record SensorReadingResponse(

        String sensorId,

        double value,

        Instant timestamp

) {
}