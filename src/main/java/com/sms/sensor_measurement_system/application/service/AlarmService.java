package com.sms.sensor_measurement_system.application.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sms.sensor_measurement_system.domain.entity.Alarm;
import com.sms.sensor_measurement_system.domain.repository.AlarmRepository;

@Service
public class AlarmService {

    private static final Logger log =
            LoggerFactory.getLogger(AlarmService.class);

    private final AlarmRepository repository;

    public AlarmService(
            AlarmRepository repository) {

        this.repository = repository;

    }

    public void raiseAlarm(String sensorId,
                           String sensorType,
                           double value) {

        String message = String.format(
                "ALARM :: Sensor=%s Type=%s Value=%s",
                sensorId,
                sensorType,
                value);

        Alarm alarm =
                new Alarm(
                        sensorId,
                        sensorType,
                        value,
                        message);

        repository.save(alarm);

        log.warn(message);

    }

}