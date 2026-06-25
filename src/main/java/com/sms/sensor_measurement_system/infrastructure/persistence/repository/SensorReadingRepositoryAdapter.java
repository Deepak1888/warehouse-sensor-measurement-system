package com.sms.sensor_measurement_system.infrastructure.persistence.repository;


import com.sms.sensor_measurement_system.domain.entity.SensorReading;
import com.sms.sensor_measurement_system.domain.repository.SensorReadingRepository;
import com.sms.sensor_measurement_system.domain.repository.SpringSensorReadingRepository;
import com.sms.sensor_measurement_system.infrastructure.persistence.mapper.SensorReadingMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SensorReadingRepositoryAdapter
        implements SensorReadingRepository {

    private final SpringSensorReadingRepository repository;

    public SensorReadingRepositoryAdapter(
            SpringSensorReadingRepository repository) {

        this.repository = repository;
    }

    @Override
    public Mono<SensorReading> save(SensorReading reading) {

        return repository

                .save(SensorReadingMapper.toEntity(reading))

                .map(SensorReadingMapper::toDomain);

    }
}