package com.sms.sensor_measurement_system.infrastructure.udp;


import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;
import com.sms.sensor_measurement_system.application.usecase.ReceiveSensorReadingUseCase;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;

@Component
public class UdpSensorServer implements ApplicationRunner {

    private static final Logger log =
            LoggerFactory.getLogger(UdpSensorServer.class);

    private static final int TEMPERATURE_PORT = 13346;
    private static final int HUMIDITY_PORT = 13345;

    private final ReceiveSensorReadingUseCase useCase;

    private Connection temperatureConnection;
    private Connection humidityConnection;

    public UdpSensorServer(
            ReceiveSensorReadingUseCase useCase) {

        this.useCase = useCase;
    }

    @Override
    public void run(ApplicationArguments args) {

        startTemperatureServer();
        startHumidityServer();

    }

    private void startTemperatureServer() {

        temperatureConnection = UdpServer.create()

                .port(TEMPERATURE_PORT)

                .handle((in, out) ->

                        in.receive()

                                .asString()

                                .doOnNext(msg ->
                                        log.info("Temperature UDP Received : {}", msg))

                                .flatMap(msg ->
                                        process(msg, SensorType.TEMPERATURE))

                                .then()

                )

                .bindNow();

        log.info("Temperature UDP Server started on port {}", TEMPERATURE_PORT);

    }

    private void startHumidityServer() {

        humidityConnection = UdpServer.create()

                .port(HUMIDITY_PORT)

                .handle((in, out) ->

                        in.receive()

                                .asString()

                                .doOnNext(msg ->
                                        log.info("Humidity UDP Received : {}", msg))

                                .flatMap(msg ->
                                        process(msg, SensorType.HUMIDITY))

                                .then()

                )

                .bindNow();

        log.info("Humidity UDP Server started on port {}", HUMIDITY_PORT);

    }

    private Mono<Void> process(
            String message,
            SensorType sensorType) {

        SensorMessage sensorMessage =
                SensorMessageParser.parse(message);

        return useCase.execute(

                new ReceiveSensorReadingCommand(

                        sensorMessage.sensorId(),

                        sensorMessage.value(),

                        sensorType

                )

        );

    }

}