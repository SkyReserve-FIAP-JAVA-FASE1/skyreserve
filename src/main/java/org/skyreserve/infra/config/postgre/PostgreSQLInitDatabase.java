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

        // TABLE AERONAVE
        databaseClient.sql("CREATE TABLE IF NOT exists aeronave (\n" +
                        "    id SERIAL PRIMARY KEY,\n" +
                        "    matricula VARCHAR(255) NOT NULL UNIQUE,\n" +
                        "    limite_assentos INT NOT NULL DEFAULT 1\n" +
                        ");")
                .fetch().rowsUpdated().block();


        // TABLE ASSENTO
        databaseClient.sql("CREATE TABLE IF NOT exists assento (\n" +
                        "    id BIGSERIAL PRIMARY KEY,\n" +
                        "    descricao VARCHAR(255) NOT NULL,\n" +
                        "    reservado BOOLEAN DEFAULT false,\n" +
                        "  \taeronave_id BIGINT NOT NULL,\n" +
                        "    FOREIGN KEY (aeronave_id) REFERENCES aeronave(id) ON DELETE CASCADE\n" +
                        ")")
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


        // TABLE VOO
        databaseClient.sql("CREATE TABLE IF NOT EXISTS voo (\n" +
                        "    id SERIAL PRIMARY KEY,\n" +
                        "    origem VARCHAR(255) NOT NULL,\n" +
                        "    destino VARCHAR(255) NOT NULL,\n" +
                        "    data_hora_partida TIMESTAMP NOT NULL,\n" +
                        "    data_hora_chegada TIMESTAMP NOT NULL,\n" +
                        "    aeronave_id INT NOT NULL,\n" +
                        "    CONSTRAINT fk_aeronave\n" +
                        "        FOREIGN KEY (aeronave_id) REFERENCES aeronave (id)\n" +
                        ");")
                .fetch().rowsUpdated().block();


        // TABLE PAGAMENTO
        databaseClient.sql("CREATE TABLE IF NOT EXISTS pagamento (\n" +
                        "    id SERIAL PRIMARY KEY,\n" +
                        "    data_pagamento TIMESTAMP NOT NULL,\n" +
                        "    valor_total NUMERIC(15, 2) NOT NULL,\n" +
                        "    status_pagamento VARCHAR(50) NOT NULL\n" +
                        ");")
                .fetch().rowsUpdated().block();




    }
}
