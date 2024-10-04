package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReservaRepository extends ReactiveCrudRepository<ReservaEntity, Long> {
}