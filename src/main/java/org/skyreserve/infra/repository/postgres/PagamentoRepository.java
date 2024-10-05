package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
}