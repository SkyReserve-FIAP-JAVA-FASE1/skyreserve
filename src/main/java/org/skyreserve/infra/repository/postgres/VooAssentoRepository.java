package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.VooAssentoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VooAssentoRepository extends ReactiveCrudRepository<VooAssentoEntity, Long> {
    Flux<VooAssentoEntity> findAllByOrderByIdAsc();
    Mono<VooAssentoEntity> findAllByVooIdOrderByNomeAssentoAsc(Long vooId);
}