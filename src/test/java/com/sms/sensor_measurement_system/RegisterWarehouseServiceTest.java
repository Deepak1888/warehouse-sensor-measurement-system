package com.sms.sensor_measurement_system;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sms.sensor_measurement_system.application.command.RegisterWarehouseCommand;
import com.sms.sensor_measurement_system.application.service.RegisterWarehouseService;
import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.repository.WarehouseRepository;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RegisterWarehouseServiceTest {

    @Mock
    WarehouseRepository repository;

    @InjectMocks
    RegisterWarehouseService service;

    @Test
    void shouldRegisterWarehouse() {

        Warehouse warehouse =
                new Warehouse(
                        WarehouseId.generate(),
                        "Warehouse-A");

        when(repository.save(any()))
                .thenReturn(Mono.just(warehouse));

        Mono<Warehouse> result =
                service.execute(
                        new RegisterWarehouseCommand(
                                "Warehouse-A"));

        StepVerifier.create(result)
                .expectNextMatches(
                        w -> w.getName()
                                .equals("Warehouse-A"))
                .verifyComplete();

    }

}