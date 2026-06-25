package com.sms.sensor_measurement_system.application.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;
import com.sms.sensor_measurement_system.application.usecase.ReceiveSensorReadingUseCase;
import com.sms.sensor_measurement_system.domain.entity.SensorReading;
import com.sms.sensor_measurement_system.domain.repository.SensorReadingRepository;
import com.sms.sensor_measurement_system.domain.valueobject.MeasurementValue;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;

import reactor.core.publisher.Mono;

@Service
public class ReceiveSensorReadingService
implements ReceiveSensorReadingUseCase {

    private final SensorReadingRepository repository;

    public ReceiveSensorReadingService(
            SensorReadingRepository repository) {

        this.repository = repository;

    }

    @Override
    public Mono<Void> execute(
            ReceiveSensorReadingCommand command) {

        SensorReading reading =
                new SensorReading(

                        SensorId.of(command.sensorId()),

                        new MeasurementValue(
                                command.value()),

                        Instant.now()

                );

        return repository
                .save(reading)
                .then();

    }

}