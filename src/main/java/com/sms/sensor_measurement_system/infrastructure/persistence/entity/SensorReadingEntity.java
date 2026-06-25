package com.sms.sensor_measurement_system.infrastructure.persistence.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Table("sensor_reading")
public class SensorReadingEntity {

    @Id
    private Long id;

    private String sensorId;

    private Double measurementValue;

    private Instant timestamp;

    private String sensorType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}


	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public Double getMeasurementValue() {
		return measurementValue;
	}

	public void setMeasurementValue(Double measurementValue) {
		this.measurementValue = measurementValue;
	}

}