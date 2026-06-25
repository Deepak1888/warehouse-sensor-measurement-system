CREATE TABLE warehouse (

    id VARCHAR(50) PRIMARY KEY,

    name VARCHAR(255)

);

CREATE TABLE sensor_reading (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    sensor_id VARCHAR(50),

    measurementValue DOUBLE,

    timestamp TIMESTAMP,

    sensor_type VARCHAR(50)

);