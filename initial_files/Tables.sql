-- AERONAVE
CREATE TABLE IF NOT exists aeronave (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(255) NOT NULL UNIQUE,
    limite_assentos INT NOT NULL DEFAULT 1
);


-- ASSENTO
CREATE TABLE IF NOT exists assento (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    reservado BOOLEAN DEFAULT false,
  	aeronave_id BIGINT NOT NULL,
    FOREIGN KEY (aeronave_id) REFERENCES aeronave(id) ON DELETE CASCADE
);


-- PASSAGEIRO
CREATE TABLE IF NOT EXISTS passageiro (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    numero_passaporte VARCHAR(20) UNIQUE,
    data_nascimento DATE NOT NULL,
    celular VARCHAR(15)
);

-- VOO
CREATE TABLE IF NOT EXISTS voo (
    id SERIAL PRIMARY KEY,
    origem VARCHAR(255) NOT NULL,
    destino VARCHAR(255) NOT NULL,
    data_hora_partida TIMESTAMP NOT NULL,
    data_hora_chegada TIMESTAMP NOT NULL,
    aeronave_id INT NOT NULL,
    CONSTRAINT fk_aeronave
        FOREIGN KEY (aeronave_id) REFERENCES aeronave (id)
);

-- PAGAMENTO
CREATE TABLE IF NOT EXISTS pagamento (
    id SERIAL PRIMARY KEY,
    data_pagamento TIMESTAMP NOT NULL,
    valor_total NUMERIC(15, 2) NOT NULL,
    status_pagamento VARCHAR(50) NOT NULL
);


-- RESERVA
CREATE TABLE IF NOT EXISTS reserva (
    id BIGSERIAL PRIMARY KEY,
    passageiro_id BIGINT NOT NULL,
    voo_id BIGINT NOT NULL,
    assento_id BIGINT NOT NULL,
    pagamento_id BIGINT NOT NULL,
    data_da_reserva TIMESTAMP NOT NULL,
    bagagem BOOLEAN DEFAULT FALSE,
    tipo_voo VARCHAR(50) NOT NULL,
    valor_reserva DECIMAL(10, 2) NOT NULL,
    
    CONSTRAINT fk_passageiro FOREIGN KEY (passageiro_id) REFERENCES passageiro(id),
    CONSTRAINT fk_voo FOREIGN KEY (voo_id) REFERENCES voo(id),
    CONSTRAINT fk_assento FOREIGN KEY (assento_id) REFERENCES assento(id),
    CONSTRAINT fk_pagamento FOREIGN KEY (pagamento_id) REFERENCES pagamento(id)
);


