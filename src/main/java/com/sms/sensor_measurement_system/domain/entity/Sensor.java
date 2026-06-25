package com.sms.sensor_measurement_system.domain.entity;


import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

public class Sensor {

    private final SensorId id;

    private final SensorType type;

    public Sensor(SensorId id,
                  SensorType type) {

        this.id = id;
        this.type = type;
    }

    public SensorId getId() {
        return id;
    }

    public SensorType getType() {
        return type;
    }
}