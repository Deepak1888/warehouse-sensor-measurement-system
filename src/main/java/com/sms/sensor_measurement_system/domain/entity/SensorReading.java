package com.sms.sensor_measurement_system.domain.entity;


import com.sms.sensor_measurement_system.domain.valueobject.MeasurementValue;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

import java.time.Instant;

public class SensorReading {

    private final SensorId sensorId;

    private final MeasurementValue value;

    private final Instant timestamp;

    private final SensorType sensorType;
    
    public SensorReading(SensorId sensorId,
    		SensorType sensorType,
                         MeasurementValue value,
                         Instant timestamp) {

        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.value = value;
        this.timestamp = timestamp;

    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public MeasurementValue getValue() {
        return value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

	public SensorType getSensorType() {
		return sensorType;
	}
}