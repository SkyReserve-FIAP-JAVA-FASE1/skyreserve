package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.ReembolsoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReembolsoRepository extends JpaRepository<ReembolsoEntity, Long> {
}