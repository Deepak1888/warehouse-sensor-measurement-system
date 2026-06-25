package com.sms.sensor_measurement_system.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateWarehouseRequest(

        @NotBlank
        String name

) {
}