package com.sms.sensor_measurement_system.infrastructure.udp;

import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

public final class SensorMessageParser {

    private SensorMessageParser() {
    }

    public static SensorMessage parse(String message) {

        String[] fields = message.split(";");

        String sensorId = fields[0].split("=")[1];

        double value = Double.parseDouble(
                fields[1].split("=")[1]);

        return new SensorMessage(sensorId, value,SensorType.TEMPERATURE);
    }
}