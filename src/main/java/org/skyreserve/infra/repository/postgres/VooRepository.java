package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.VooEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VooRepository extends ReactiveCrudRepository<VooEntity, Long> {
}