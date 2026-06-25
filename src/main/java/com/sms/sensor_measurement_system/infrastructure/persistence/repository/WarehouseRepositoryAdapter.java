package com.sms.sensor_measurement_system.infrastructure.persistence.repository;


import com.sms.sensor_measurement_system.domain.aggregate.Warehouse;
import com.sms.sensor_measurement_system.domain.repository.WarehouseRepository;
import com.sms.sensor_measurement_system.domain.valueobject.WarehouseId;
import com.sms.sensor_measurement_system.infrastructure.persistence.mapper.WarehouseMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class WarehouseRepositoryAdapter
        implements WarehouseRepository {

    private final SpringWarehouseRepository repository;

    public WarehouseRepositoryAdapter(SpringWarehouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Warehouse> save(Warehouse warehouse) {

        return repository.save(
                        WarehouseMapper.toEntity(warehouse))
                .map(WarehouseMapper::toDomain);

    }

    @Override
    public Mono<Warehouse> findById(WarehouseId id) {

        return repository.findById(id.value())
                .map(WarehouseMapper::toDomain);

    }

    @Override
    public Flux<Warehouse> findAll() {

        return repository.findAll()
                .map(WarehouseMapper::toDomain);

    }

}