package com.sms.sensor_measurement_system.domain.aggregate;


import com.sms.sensor_measurement_system.domain.entity.Sensor;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private final WarehouseId id;

    private final String name;

    private final List<Sensor> sensors = new ArrayList<>();

    public Warehouse(WarehouseId id,
                     String name) {

        this.id = id;
        this.name = name;
    }

    public void registerSensor(Sensor sensor) {

        sensors.add(sensor);

    }

    public List<Sensor> getSensors() {

        return List.copyOf(sensors);

    }

    public WarehouseId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}