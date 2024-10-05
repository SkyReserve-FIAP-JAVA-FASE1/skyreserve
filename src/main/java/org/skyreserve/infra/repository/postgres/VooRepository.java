package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.PassageiroEntity;
import org.skyreserve.domain.entity.VooEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VooRepository extends JpaRepository<VooEntity, Long> {
}