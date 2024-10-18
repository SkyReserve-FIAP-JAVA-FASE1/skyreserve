package org.skyreserve.infra.repository.postgres.impl;

import lombok.RequiredArgsConstructor;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.repository.postgres.VooCustomRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class VooCustomRepositoryImpl implements VooCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<VooEntity> findByVooParamters(
            String origem,
            String destino,
            LocalDateTime dataHoraPartidaMin,
            LocalDateTime dataHoraPartidaMax,
            int page,
            int size,
            String orderBy,
            String direction
    ) {

        direction = !"ASC".equalsIgnoreCase(direction) &&
                    !"DESC".equalsIgnoreCase(direction) ? "ASC" : direction;

        orderBy =   !"data_hora_partida".equals(orderBy) &&
                    !"data_hora_chegada".equals(orderBy) ? "data_hora_partida" : orderBy;

        String query = "SELECT * FROM voo WHERE origem = :origem AND destino = :destino " +
                "AND data_hora_partida BETWEEN :dataHoraPartidaMin AND :dataHoraPartidaMax " +
                "ORDER BY " + orderBy + " " + direction + " " +
                "LIMIT :size OFFSET :offset";

        return databaseClient.sql(query)
                .bind("origem", origem)
                .bind("destino", destino)
                .bind("dataHoraPartidaMin", dataHoraPartidaMin)
                .bind("dataHoraPartidaMax", dataHoraPartidaMax)
                .bind("size", size)
                .bind("offset", page * size)
                .map((row, rowMetadata) -> VooEntity.builder()
                        .id(row.get("id", Long.class))
                        .origem(row.get("origem", String.class))
                        .destino(row.get("destino", String.class))
                        .dataHoraPartida(row.get("data_hora_partida", LocalDateTime.class))
                        .dataHoraChegada(row.get("data_hora_chegada", LocalDateTime.class))
                        .aeronaveId(row.get("aeronave_id", Long.class))
                        .build()
                ).all();
    }

}
