package org.skyreserve.app.service.kafka.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.AssentoService;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.infra.mapper.AssentoMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class KafkaConsumer {

    private final Flux<?> kafkaFlux;

    public KafkaConsumer(ReceiverOptions<String, String> receiverOptions,
                                 ObjectMapper objectMapper,
                                 AssentoService service,
                                 AssentoMapper assentoMapper) {

        KafkaReceiver<String, String> kafkaReceiver = KafkaReceiver.create(receiverOptions);

        kafkaFlux = kafkaReceiver.receive()
                .flatMap(record -> {
                    String message = record.value();
                    try {
                        log.info("Tópico consumido com sucesso no Kafka...");

                        AssentoDTO assentoDTO = objectMapper.readValue(message, new TypeReference<>() {});
                        log.info("Mapper realizado com sucesso");

                        return service.save(assentoDTO)
                                .doOnSuccess(entity -> {
                                    log.info("Assento salvo com sucesso: {}", assentoMapper.toDTO(entity).toString());
                                    record.receiverOffset().acknowledge();
                                })
                                .doOnError(error -> log.error("Erro ao salvar no banco de dados: ", error));

                    } catch (Exception e) {
                        log.error("Erro ao processar a mensagem: ", e);
                        record.receiverOffset().acknowledge();
                        return Mono.empty();
                    }
                });
    }

    /*
        Reatividade não depende da ausência de subscribe():
        O subscribe() é fundamental para iniciar a cadeia reativa.
        Sem ele, a pipeline reativa não é executada.
        No contexto de um consumer Kafka, usar subscribe() manualmente garante
        que as mensagens sejam consumidas continuamente.
    */
    @PostConstruct
    public void startConsuming() {
        kafkaFlux.subscribe();
    }
}
