package com.sms.sensor_measurement_system.infrastructure.persistence.mapper;

import com.sms.sensor_measurement_system.domain.entity.SensorReading;
import com.sms.sensor_measurement_system.domain.valueobject.MeasurementValue;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;
import com.sms.sensor_measurement_system.infrastructure.persistence.entity.SensorReadingEntity;

public class SensorReadingMapper {

    public static SensorReadingEntity toEntity(
            SensorReading reading) {

        SensorReadingEntity entity =
                new SensorReadingEntity();

        entity.setSensorId(
                reading.getSensorId().value());

        entity.setMeasurementValue(
                reading.getValue().value());

        entity.setTimestamp(
                reading.getTimestamp());
        entity.setSensorType(
                reading.getSensorType().name());

        return entity;

    }
    public static SensorReading toDomain(
            SensorReadingEntity entity) {

    	return new SensorReading(
    	        SensorId.of(entity.getSensorId()),
    	        SensorType.valueOf(entity.getSensorType()),
    	        new MeasurementValue(entity.getMeasurementValue()),
    	        entity.getTimestamp()
    	);
}
}