package com.sms.sensor_measurement_system.application.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;
import com.sms.sensor_measurement_system.application.usecase.ReceiveSensorReadingUseCase;
import com.sms.sensor_measurement_system.common.Thresholds;
import com.sms.sensor_measurement_system.domain.entity.SensorReading;
import com.sms.sensor_measurement_system.domain.repository.SensorReadingRepository;
import com.sms.sensor_measurement_system.domain.valueobject.MeasurementValue;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;

import reactor.core.publisher.Mono;

@Service
public class ReceiveSensorReadingService
implements ReceiveSensorReadingUseCase {

    private final SensorReadingRepository repository;
    private final AlarmService alarmService;

    public ReceiveSensorReadingService(
            SensorReadingRepository repository, AlarmService alarmService) {

        this.repository = repository;
        this.alarmService = alarmService;

    }

    @Override
    public Mono<Void> execute(
            ReceiveSensorReadingCommand command) {

    	SensorReading reading = new SensorReading(
                SensorId.of(command.sensorId()),
                command.sensorType(),
                new MeasurementValue(command.value()),
                Instant.now()
        );

    	return repository.save(reading)
                .flatMap(savedReading -> {

                    if (savedReading.getSensorType() == SensorType.TEMPERATURE
                            && savedReading.getValue().value() > Thresholds.TEMPERATURE_THRESHOLD) {

                        alarmService.raiseAlarm(
                                savedReading.getSensorId().value(),
                                savedReading.getSensorType().name(),
                                savedReading.getValue().value());
                    }

                    if (savedReading.getSensorType() == SensorType.HUMIDITY
                            && savedReading.getValue().value() > Thresholds.HUMIDITY_THRESHOLD) {

                        alarmService.raiseAlarm(
                                savedReading.getSensorId().value(),
                                savedReading.getSensorType().name(),
                                savedReading.getValue().value());
                    }

                    return Mono.empty();
                });
    }

}