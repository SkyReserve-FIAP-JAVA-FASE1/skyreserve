package org.skyreserve.infra.config.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostgreSQLInitDatabase {

    @Autowired
    private DatabaseClient databaseClient;

    @PostConstruct
    public void init() {

        // Tabela Assento
        databaseClient.sql("CREATE TABLE IF NOT EXISTS assento ( id BIGSERIAL PRIMARY KEY, descricao VARCHAR(255) NOT NULL, reservado BOOLEAN DEFAULT FALSE)")
                .fetch().rowsUpdated().block();
    }
}
