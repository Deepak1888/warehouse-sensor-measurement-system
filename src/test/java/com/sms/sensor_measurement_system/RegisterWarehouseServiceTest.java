package com.sms.sensor_measurement_system;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sms.sensor_measurement_system.application.command.ReceiveSensorReadingCommand;
import com.sms.sensor_measurement_system.application.command.RegisterWarehouseCommand;
import com.sms.sensor_measurement_system.application.service.AlarmService;
import com.sms.sensor_measurement_system.application.service.ReceiveSensorReadingService;
import com.sms.sensor_measurement_system.application.service.RegisterWarehouseService;
import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.entity.SensorReading;
import com.sms.sensor_measurement_system.domain.repository.SensorReadingRepository;
import com.sms.sensor_measurement_system.domain.repository.WarehouseRepository;
import com.sms.sensor_measurement_system.domain.valueobject.MeasurementValue;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RegisterWarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private RegisterWarehouseService registerWarehouseService;


    @Mock
    private SensorReadingRepository sensorReadingRepository;

    @Mock
    private AlarmService alarmService;

    @InjectMocks
    private ReceiveSensorReadingService receiveSensorReadingService;

    @Test
    void shouldRegisterWarehouse() {

        Warehouse warehouse = new Warehouse(
                WarehouseId.generate(),
                "Warehouse Test");

        when(warehouseRepository.save(any(Warehouse.class)))
                .thenReturn(Mono.just(warehouse));

        StepVerifier.create(

                registerWarehouseService.execute(

                        new RegisterWarehouseCommand(
                                "Warehouse Test")

                )

        )

        .expectNextMatches(saved ->
                saved.getName().equals("Warehouse Test"))

        .verifyComplete();

        verify(warehouseRepository)
                .save(any(Warehouse.class));
    }

    @Test
    void shouldSaveReadingWithoutAlarm() {

        SensorReading reading = new SensorReading(
                SensorId.of("t1"),
                SensorType.TEMPERATURE,
                new MeasurementValue(30),
                Instant.now());

        when(sensorReadingRepository.save(any(SensorReading.class)))
                .thenReturn(Mono.just(reading));

        StepVerifier.create(

                receiveSensorReadingService.execute(

                        new ReceiveSensorReadingCommand(
                                "t1",
                                30,
                                SensorType.TEMPERATURE)

                )

        ).verifyComplete();

        verify(sensorReadingRepository)
                .save(any(SensorReading.class));

        verify(alarmService, never())
                .raiseAlarm(any(), any(), anyDouble());
    }

    @Test
    void shouldRaiseAlarmWhenThresholdExceeded() {

        SensorReading reading = new SensorReading(
                SensorId.of("t1"),
                SensorType.TEMPERATURE,
                new MeasurementValue(40),
                Instant.now());

        when(sensorReadingRepository.save(any(SensorReading.class)))
                .thenReturn(Mono.just(reading));

        StepVerifier.create(

                receiveSensorReadingService.execute(

                        new ReceiveSensorReadingCommand(
                                "t1",
                                40,
                                SensorType.TEMPERATURE)

                )

        ).verifyComplete();

        verify(sensorReadingRepository)
                .save(any(SensorReading.class));

        verify(alarmService)
                .raiseAlarm(
                        eq("t1"),
                        eq("TEMPERATURE"),
                        eq(40.0));
    }

}