package com.sms.sensor_measurement_system.infrastructure.udp;

import org.springframework.stereotype.Component;

import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;
import com.sms.sensor_measurement_system.application.usecase.ReceiveSensorReadingUseCase;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;
import reactor.netty.udp.UdpServer;

@Component
public class TemperatureUdpServer {

    private final ReceiveSensorReadingUseCase useCase;

    public TemperatureUdpServer(
            ReceiveSensorReadingUseCase useCase) {

        this.useCase = useCase;

    }

    @PostConstruct
    public void start() {

        UdpServer.create()

                .port(13344)

                .handle((in, out) ->

                        in.receive()

                                .asString()

                                .flatMap(message ->

                                        process(message,
                                                "TEMPERATURE"))

                                .then()

                )

                .bindNow();

    }

    private Mono<Void> process(
            String message,
            String sensorType) {

        SensorMessage parsed =
                SensorMessageParser.parse(message);

        return useCase.execute(

                new ReceiveSensorReadingCommand(

                        parsed.sensorId(),

                        parsed.value(),

                        parsed.sensorType()

                ));

    }

}