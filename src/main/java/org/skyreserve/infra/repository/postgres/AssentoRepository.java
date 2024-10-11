package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AssentoRepository extends ReactiveCrudRepository<AssentoEntity, Long> {
}