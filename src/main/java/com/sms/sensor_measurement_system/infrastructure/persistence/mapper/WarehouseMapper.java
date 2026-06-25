package com.sms.sensor_measurement_system.infrastructure.persistence.mapper;


import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;
import com.sms.sensor_measurement_system.infrastructure.persistence.entity.WarehouseEntity;

public final class WarehouseMapper {

    private WarehouseMapper() {
    }

    public static WarehouseEntity toEntity(Warehouse warehouse) {

        return new WarehouseEntity(
                warehouse.getId().value(),
                warehouse.getName()
        );

    }

    public static Warehouse toDomain(WarehouseEntity entity) {

        return new Warehouse(
                WarehouseId.of(entity.getId()),
                entity.getName()
        );

    }

}