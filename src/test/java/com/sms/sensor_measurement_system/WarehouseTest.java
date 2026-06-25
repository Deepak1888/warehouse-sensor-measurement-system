package com.sms.sensor_measurement_system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.entity.Sensor;
import com.sms.sensor_measurement_system.domain.valueobject.SensorId;
import com.sms.sensor_measurement_system.domain.valueobject.SensorType;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;

public class WarehouseTest {
	
	@Test
	void shouldRegisterSensor() {

	    Warehouse warehouse =
	            new Warehouse(
	                    WarehouseId.generate(),
	                    "Warehouse-A");

	    Sensor sensor =
	            new Sensor(
	                    SensorId.of("temp-1"),
	                    SensorType.TEMPERATURE);

	    warehouse.registerSensor(sensor);

	    assertEquals(1,
	            warehouse.getSensors().size());

	}

}
