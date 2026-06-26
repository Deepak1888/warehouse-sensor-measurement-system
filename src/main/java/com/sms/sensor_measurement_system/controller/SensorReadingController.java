package com.sms.sensor_measurement_system.controller;


import com.sms.sensor_measurement_system.dto.SensorReadingResponse;
import com.sms.sensor_measurement_system.domain.repository.SensorReadingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


import com.sms.sensor_measurement_system.dto.SensorReadingRequest;
import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;
import com.sms.sensor_measurement_system.application.usecase.ReceiveSensorReadingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    private final SensorReadingRepository repository;
    private final ReceiveSensorReadingUseCase useCase;

    public SensorReadingController(
            SensorReadingRepository repository,
            ReceiveSensorReadingUseCase useCase) {

        this.repository = repository;
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> createReading(
            @RequestBody SensorReadingRequest request) {

        return useCase.execute(
                new ReceiveSensorReadingCommand(
                        request.sensorId(),
                        request.value(),
                        request.sensorType()
                ))
                .thenReturn("Sensor reading processed successfully.");
    }

    @GetMapping
    public Flux<SensorReadingResponse> getReadings() {

        return repository.findAll()
                .map(reading -> new SensorReadingResponse(
                        reading.getSensorId().value(),
                        reading.getSensorType().name(),
                        reading.getValue().value(),
                        reading.getTimestamp()
                ));
    }
}