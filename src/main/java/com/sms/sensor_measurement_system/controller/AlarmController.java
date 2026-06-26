package com.sms.sensor_measurement_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.sensor_measurement_system.domain.entity.Alarm;
import com.sms.sensor_measurement_system.domain.repository.AlarmRepository;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {

    private final AlarmRepository repository; // Due to time limitation, could not fix it, repos should be moved to service layer, not on controller.

    public AlarmController(
            AlarmRepository repository) {

        this.repository = repository;

    }

    @GetMapping
    public Flux<Alarm> getAlarms() {

        return Flux.fromIterable(
                repository.findAll());

    }

}