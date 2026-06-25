package com.sms.sensor_measurement_system.application.service;


import com.sms.sensor_measurement_system.application.command.RegisterWarehouseCommand;
import com.sms.sensor_measurement_system.application.usecase.RegisterWarehouseUseCase;
import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.repository.WarehouseRepository;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegisterWarehouseService
        implements RegisterWarehouseUseCase {

    private final WarehouseRepository repository;

    public RegisterWarehouseService(
            WarehouseRepository repository) {

        this.repository = repository;
    }

    @Override
    public Mono<Warehouse> execute(
            RegisterWarehouseCommand command) {

        Warehouse warehouse =
                new Warehouse(
                        WarehouseId.generate(),
                        command.name());

        return repository.save(warehouse);

    }

}