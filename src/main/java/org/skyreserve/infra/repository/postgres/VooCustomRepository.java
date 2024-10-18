package org.skyreserve.infra.repository.postgres;

import org.skyreserve.domain.entity.VooEntity;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface VooCustomRepository {

    Flux<VooEntity> findByVooParamters(
            String origem,
            String destino,
            LocalDateTime dataHoraPartidaMin,
            LocalDateTime dataHoraPartidaMax,
            int page,
            int size,
            String orderBy,
            String direction
    );
}