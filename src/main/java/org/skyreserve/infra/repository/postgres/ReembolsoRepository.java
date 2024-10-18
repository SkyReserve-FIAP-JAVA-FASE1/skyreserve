package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.ReembolsoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReembolsoRepository extends ReactiveCrudRepository<ReembolsoEntity, Long> {
}