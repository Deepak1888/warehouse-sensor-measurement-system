package com.sms.sensor_measurement_system.domain.repository;


import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WarehouseRepository {

    Mono<Warehouse> save(Warehouse warehouse);

    Mono<Warehouse> findById(WarehouseId id);

    Flux<Warehouse> findAll();

}