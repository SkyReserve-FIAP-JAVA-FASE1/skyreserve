package org.skyreserve.infra.repository.postgres.impl;

import lombok.RequiredArgsConstructor;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.repository.postgres.VooAssentoCustomRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class VooAssentoCustomRepositoryImpl implements VooAssentoCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Mono<Void> bloquearAssento(long vooAssentoId) {
        String query = "UPDATE vooassento SET reservado = true WHERE id = :vooAssentoId";
        return databaseClient.sql(query)
                .bind("vooAssentoId", vooAssentoId)
                .fetch()
                .rowsUpdated()
                .then();
    }

    @Override
    public Mono<Void> desbloquearAssento(long vooAssentoId) {
        String query = "UPDATE vooassento SET reservado = false WHERE id = :vooAssentoId";
        return databaseClient.sql(query)
                .bind("vooAssentoId", vooAssentoId)
                .fetch()
                .rowsUpdated()
                .then();
    }
}
