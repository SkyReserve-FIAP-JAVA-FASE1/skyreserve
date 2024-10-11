package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.PagamentoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PagamentoRepository extends ReactiveCrudRepository<PagamentoEntity, Long> {
}