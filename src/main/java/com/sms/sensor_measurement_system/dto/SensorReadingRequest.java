package com.sms.sensor_measurement_system.dto;


import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

public record SensorReadingRequest(

        String sensorId,

        SensorType sensorType,

        double value

) {
}