package com.sms.sensor_measurement_system.application.command;

import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

public record RegisterSensorCommand(

        String warehouseId,

        String sensorId,

        SensorType sensorType

) {
}