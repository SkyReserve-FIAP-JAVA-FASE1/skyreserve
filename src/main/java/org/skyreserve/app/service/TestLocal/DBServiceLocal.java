package org.skyreserve.app.service.TestLocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

@Service
public class DBServiceLocal {

    @Value("${local}")
    boolean local;

    @Autowired
    private DatabaseClient databaseClient;

    public void instanciaDB() {
        if (local) {
            System.out.println("Iniciando a aplicação com valores iniciais.");

            // INSERÇÃO DO PASSAGEIRO
            databaseClient.sql("INSERT INTO public.passageiro\n" +
                            "(id, nome, cpf, email, numero_passaporte, data_nascimento, celular)\n" +
                            "VALUES(nextval('passageiro_id_seq'::regclass), 'Fulano de Tal', '22222222222', 'teste@teste.com', 'AHX1151', TO_DATE('31/01/2000','dd/MM/YYYY'), '(11)98888-5555');")
                    .fetch().rowsUpdated().block();


            // INSERÇÃO DO AERONAVE
            databaseClient.sql("INSERT INTO public.aeronave\n" +
                            "(id, matricula, limite_assentos)\n" +
                            "VALUES(nextval('aeronave_id_seq'::regclass), 'AEW105D', 50);")
                    .fetch().rowsUpdated().block();


            // INSERÇÃO DO ASSENTO
            databaseClient.sql("INSERT INTO public.assento\n" +
                            "(id, descricao, reservado, aeronave_id)\n" +
                            "VALUES(nextval('assento_id_seq'::regclass), 'A1', false, 1);")
                    .fetch().rowsUpdated().block();


            // INSERÇÃO DO VÔO
            databaseClient.sql("INSERT INTO public.voo\n" +
                            "(id, origem, destino, data_hora_partida, data_hora_chegada, aeronave_id)\n" +
                            "VALUES(nextval('voo_id_seq'::regclass),\n" +
                            "'São Paulo', \n" +
                            "'Rio de Janeiro', TO_DATE('31/01/2000','dd/MM/YYYY'), TO_DATE('01/02/2000','dd/MM/YYYY'), 1);")
                    .fetch().rowsUpdated().block();


            // INSERÇÃO DO PAGAMENTO
            databaseClient.sql("INSERT INTO public.pagamento\n" +
                            "(id, data_pagamento, valor_total, status_pagamento)\n" +
                            "VALUES(nextval('pagamento_id_seq'::regclass), TO_DATE('10/07/2024','dd/MM/YYYY'), 589.45, 'PENDENTE_PAGAMENTO');")
                    .fetch().rowsUpdated().block();


            // INSERÇÃO DO RESERVA
            databaseClient.sql("INSERT INTO public.reserva\n" +
                            "(id, passageiro_id, voo_id, assento_id, pagamento_id, data_da_reserva, bagagem, tipo_voo, valor_reserva)\n" +
                            "VALUES(nextval('reserva_id_seq'::regclass), 1, 1, 1, 1, TO_DATE('10/07/2024','dd/MM/YYYY'), false, 'IDA_E_VOLTA', 589.45);")
                    .fetch().rowsUpdated().block();


        }

    }
}
