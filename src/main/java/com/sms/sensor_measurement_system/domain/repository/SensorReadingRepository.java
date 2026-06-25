package com.sms.sensor_measurement_system.domain.repository;


import com.sms.sensor_measurement_system.domain.entity.SensorReading;

import reactor.core.publisher.Mono;

public interface SensorReadingRepository {

    Mono<SensorReading> save(
            SensorReading reading);


}