package com.sms.sensor_measurement_system.domain.repository;


import com.sms.sensor_measurement_system.domain.entity.SensorReading;

import reactor.core.publisher.Mono;


import reactor.core.publisher.Flux;

public interface SensorReadingRepository {

    Mono<SensorReading> save(SensorReading reading);

    Flux<SensorReading> findAll();
}