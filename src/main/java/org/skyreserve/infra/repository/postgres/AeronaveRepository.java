package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.AeronaveEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AeronaveRepository extends ReactiveCrudRepository<AeronaveEntity, Long> {
}