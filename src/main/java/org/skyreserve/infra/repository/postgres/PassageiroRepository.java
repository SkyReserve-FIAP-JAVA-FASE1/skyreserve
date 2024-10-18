package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.PassageiroEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PassageiroRepository extends ReactiveCrudRepository<PassageiroEntity, Long> {
    Mono<PassageiroEntity> findByCpf(String cpf);
}