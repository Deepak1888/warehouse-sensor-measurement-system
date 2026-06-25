package com.sms.sensor_measurement_system.application.service;

import org.springframework.stereotype.Service;

import com.sms.sensor_measurement_system.application.command.RegisterSensorCommand;
import com.sms.sensor_measurement_system.application.usecase.RegisterSensorUseCase;
import com.sms.sensor_measurement_system.domain.entity.Sensor;
import com.sms.sensor_measurement_system.domain.repository.WarehouseRepository;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;

import reactor.core.publisher.Mono;

@Service
public class RegisterSensorService
        implements RegisterSensorUseCase {

    private final WarehouseRepository repository;

    public RegisterSensorService(
            WarehouseRepository repository) {

        this.repository = repository;
    }

    @Override
    public Mono<Void> execute(RegisterSensorCommand command) {

        return repository

                .findById(
                        WarehouseId.of(command.warehouseId()))

                .flatMap(warehouse -> {

                    warehouse.registerSensor(

                            new Sensor(

                                    SensorId.of(command.sensorId()),

                                    command.sensorType()

                            )

                    );

                    return repository.save(warehouse);

                })

                .then();

    }

}