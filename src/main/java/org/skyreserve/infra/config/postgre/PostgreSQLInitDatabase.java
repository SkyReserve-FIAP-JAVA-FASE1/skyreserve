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

        // TABLE ASSENTO
        databaseClient.sql("CREATE TABLE IF NOT exists assento (\n" +
                        "    id BIGSERIAL PRIMARY KEY,\n" +
                        "    descricao VARCHAR(255) NOT NULL,\n" +
                        "    reservado BOOLEAN DEFAULT FALSE\n" +
                        ");")
                .fetch().rowsUpdated().block();


        // TABLE PASSAGEIRO
        databaseClient.sql("CREATE TABLE IF NOT EXISTS passageiro (\n" +
                        "    id SERIAL PRIMARY KEY,\n" +
                        "    nome VARCHAR(255) NOT NULL,\n" +
                        "    cpf VARCHAR(11) NOT NULL UNIQUE,\n" +
                        "    email VARCHAR(255) NOT NULL UNIQUE,\n" +
                        "    numero_passaporte VARCHAR(20) UNIQUE,\n" +
                        "    data_nascimento DATE NOT NULL,\n" +
                        "    celular VARCHAR(15)\n" +
                        ");")
                .fetch().rowsUpdated().block();



    }
}
