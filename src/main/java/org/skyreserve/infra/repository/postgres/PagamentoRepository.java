package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    @Query("SELECT p FROM PagamentoEntity p JOIN FETCH p.reservas WHERE p.id = :id")
    PagamentoEntity findByIdWithReservas(@Param("id") Long id);

}