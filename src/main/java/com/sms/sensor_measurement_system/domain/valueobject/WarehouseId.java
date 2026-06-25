package com.sms.sensor_measurement_system.domain.valueobject;


import java.util.UUID;

public record WarehouseId(String value) {

    public static WarehouseId generate() {
        return new WarehouseId(UUID.randomUUID().toString());
    }

    public static WarehouseId of(String value) {
        return new WarehouseId(value);
    }
}