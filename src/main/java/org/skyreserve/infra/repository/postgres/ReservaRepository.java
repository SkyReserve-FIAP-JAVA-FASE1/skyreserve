package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}