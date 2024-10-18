package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AssentoRepository extends ReactiveCrudRepository<AssentoEntity, Long> {
    Flux<AssentoEntity> findAllByOrderByIdAsc();
    Flux<AssentoEntity> findAllByAeronaveId(Long aeronaveId);
}