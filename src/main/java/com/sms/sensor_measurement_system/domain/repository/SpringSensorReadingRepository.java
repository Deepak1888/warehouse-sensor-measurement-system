package com.sms.sensor_measurement_system.domain.repository;


import com.sms.sensor_measurement_system.infrastructure.persistence.entity.SensorReadingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpringSensorReadingRepository
        extends ReactiveCrudRepository<SensorReadingEntity, Long> {
}