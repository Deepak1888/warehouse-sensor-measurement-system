package com.sms.sensor_measurement_system.application.usecase;

import com.sms.sensor_measurement_system.application.command.RegisterSensorCommand;

import reactor.core.publisher.Mono;

public interface RegisterSensorUseCase {

    Mono<Void> execute(RegisterSensorCommand command);

}