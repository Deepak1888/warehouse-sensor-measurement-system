package com.sms.sensor_measurement_system.infrastructure.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("WAREHOUSE")
public class WarehouseEntity {

    @Id
    private String id;

    private String name;

    public WarehouseEntity() {
    }

    public WarehouseEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}