package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssentoRepository extends JpaRepository<AssentoEntity, Long> {
}