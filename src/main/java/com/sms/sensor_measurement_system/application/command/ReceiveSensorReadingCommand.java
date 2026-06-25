package com.sms.sensor_measurement_system.application.command;

public record ReceiveSensorReadingCommand(

        String sensorId,

        double value,

        String sensorType

) {
}