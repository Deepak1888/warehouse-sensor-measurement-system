package com.sms.sensor_measurement_system.infrastructure.persistence.repository;


import com.sms.sensor_measurement_system.infrastructure.persistence.entity.WarehouseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpringWarehouseRepository
        extends ReactiveCrudRepository<WarehouseEntity, String> {

}