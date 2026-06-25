package com.sms.sensor_measurement_system.infrastructure.udp;

import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

public record SensorMessage(
        String sensorId,
        double value,
        SensorType sensorType
) {
}