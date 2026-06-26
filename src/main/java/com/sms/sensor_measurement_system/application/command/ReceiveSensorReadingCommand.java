package com.sms.sensor_measurement_system.application.command;

import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

public record ReceiveSensorReadingCommand(

        String sensorId,

        double value,

        SensorType sensorType

) {
}