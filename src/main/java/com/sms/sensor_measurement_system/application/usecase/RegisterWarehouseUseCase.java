package com.sms.sensor_measurement_system.application.usecase;


import com.sms.sensor_measurement_system.application.command.RegisterWarehouseCommand;
import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import reactor.core.publisher.Mono;

public interface RegisterWarehouseUseCase {

    Mono<Warehouse> execute(RegisterWarehouseCommand command);

}