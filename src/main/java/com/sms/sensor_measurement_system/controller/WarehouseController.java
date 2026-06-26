package com.sms.sensor_measurement_system.controller;

import com.sms.sensor_measurement_system.dto.CreateWarehouseRequest;
import com.sms.sensor_measurement_system.dto.SensorReadingResponse;
import com.sms.sensor_measurement_system.dto.WarehouseResponse;
import com.sms.sensor_measurement_system.application.command.RegisterWarehouseCommand;
import com.sms.sensor_measurement_system.application.usecase.RegisterWarehouseUseCase;
import com.sms.sensor_measurement_system.domain.entity.Alarm;
import com.sms.sensor_measurement_system.domain.repository.SensorReadingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

	private final RegisterWarehouseUseCase useCase;
	private final SensorReadingRepository sensorReadingRepository;

	public WarehouseController(RegisterWarehouseUseCase useCase, SensorReadingRepository sensorReadingRepository) {
		this.useCase = useCase;
		this.sensorReadingRepository = sensorReadingRepository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<WarehouseResponse> createWarehouse(@RequestBody CreateWarehouseRequest request) {

		return useCase.execute(new RegisterWarehouseCommand(request.name()))

				.map(warehouse -> new WarehouseResponse(

						warehouse.getId().value(),

						warehouse.getName()

				));
	}

}