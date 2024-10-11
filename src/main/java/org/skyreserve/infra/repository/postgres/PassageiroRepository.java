package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.AssentoEntity;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PassageiroRepository extends ReactiveCrudRepository<PassageiroEntity, Long> {
}