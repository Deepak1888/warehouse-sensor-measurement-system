package com.sms.sensor_measurement_system.domain.event;


import java.time.Instant;

public record ThresholdExceededEvent(

        String warehouseId,

        String sensorId,

        double value,

        String sensorType,

        Instant occurredAt

) {}