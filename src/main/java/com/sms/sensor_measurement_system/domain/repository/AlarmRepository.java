package com.sms.sensor_measurement_system.domain.repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.sms.sensor_measurement_system.domain.entity.Alarm;




@Repository
public class AlarmRepository {

    private final List<Alarm> alarms =
            new CopyOnWriteArrayList<>();

    public void save(Alarm alarm) {

        alarms.add(alarm);

    }

    public List<Alarm> findAll() {

        return alarms;

    }

}