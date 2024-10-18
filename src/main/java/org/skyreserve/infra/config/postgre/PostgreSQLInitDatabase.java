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
                        "    nome VARCHAR(255) NOT NULL,\n" +
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
                        "    status_pagamento VARCHAR(50) NOT NULL, \n" +
                        "    reserva_id BIGINT\n" +
                        ");")
                .fetch().rowsUpdated().block();


        // TABLE REEMBOLSO
        databaseClient.sql("CREATE TABLE IF NOT EXISTS reembolso (\n" +
                        "    id BIGSERIAL PRIMARY KEY,\n" +
                        "    valor_restituicao NUMERIC(15, 2) NOT NULL,\n" +
                        "    reembolso_efetuado BOOLEAN DEFAULT FALSE,\n" +
                        "    data_solicitacao TIMESTAMP NOT NULL,\n" +
                        "    data_reembolso TIMESTAMP,\n" +
                        "    reserva_id BIGINT NOT NULL\n" +
                        ");")
                .fetch().rowsUpdated().block();


        // TABLE RESERVA
        databaseClient.sql("CREATE TABLE IF NOT EXISTS reserva (\n" +
                        "    id BIGSERIAL PRIMARY KEY,\n" +
                        "    passageiro_id BIGINT NOT NULL,\n" +
                        "    voo_id BIGINT NOT NULL,\n" +
                        "    assento_id BIGINT NOT NULL,\n" +
                        "    pagamento_id BIGINT NOT NULL,\n" +
                        "    reembolso_id BIGINT,\n" +
                        "    data_da_reserva TIMESTAMP NOT NULL,\n" +
                        "    bagagem BOOLEAN DEFAULT FALSE,\n" +
                        "    tipo_voo VARCHAR(50) NOT NULL,\n" +
                        "    valor_reserva DECIMAL(10, 2) NOT NULL,\n" +
                        "    \n" +
                        "    CONSTRAINT fk_passageiro FOREIGN KEY (passageiro_id) REFERENCES passageiro(id),\n" +
                        "    CONSTRAINT fk_voo FOREIGN KEY (voo_id) REFERENCES voo(id),\n" +
                        "    CONSTRAINT fk_assento FOREIGN KEY (assento_id) REFERENCES assento(id),\n" +
                        "    CONSTRAINT fk_pagamento FOREIGN KEY (pagamento_id) REFERENCES pagamento(id),\n" +
                        "    CONSTRAINT fk_reembolso FOREIGN KEY (reembolso_id) REFERENCES reembolso(id)\n" +
                        ");")
                .fetch().rowsUpdated().block();

        // FK PAGAMENTO -> RESERVA_ID
        databaseClient.sql("DO $$\n" +
                        "BEGIN\n" +
                        "    IF NOT EXISTS (\n" +
                        "        SELECT 1\n" +
                        "        FROM pg_constraint\n" +
                        "        WHERE conname = 'fk_pagamento_reserva_id'\n" +
                        "    ) THEN\n" +
                        "        ALTER TABLE pagamento\n" +
                        "        ADD CONSTRAINT fk_pagamento_reserva_id FOREIGN KEY (reserva_id) REFERENCES reserva(id);\n" +
                        "    END IF;\n" +
                        "END $$;")
                .fetch().rowsUpdated().block();

        // FK REEMBOLSO -> RESERVA_ID
        databaseClient.sql("DO $$\n" +
                        "BEGIN\n" +
                        "    IF NOT EXISTS (\n" +
                        "        SELECT 1\n" +
                        "        FROM pg_constraint\n" +
                        "        WHERE conname = 'fk_reembolso_reserva_id'\n" +
                        "    ) THEN\n" +
                        "        ALTER TABLE reembolso\n" +
                        "        ADD CONSTRAINT fk_reembolso_reserva_id FOREIGN KEY (reserva_id) REFERENCES reserva(id);\n" +
                        "    END IF;\n" +
                        "END $$;")
                .fetch().rowsUpdated().block();


    }
}
