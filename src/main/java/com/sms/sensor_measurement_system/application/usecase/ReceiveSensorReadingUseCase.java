package com.sms.sensor_measurement_system.application.usecase;

import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;

import reactor.core.publisher.Mono;

public interface ReceiveSensorReadingUseCase {

    Mono<Void> execute(
            ReceiveSensorReadingCommand command);

}