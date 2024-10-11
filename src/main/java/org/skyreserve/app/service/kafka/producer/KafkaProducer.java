package org.skyreserve.app.service.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.UUID;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaSender<String, String> sender;

    @Autowired
    public KafkaProducer(KafkaSender<String, String> sender) {
        this.sender = sender;
    }

    public Mono<Void> send(String topic, String payload) {
        return Mono.fromCallable(() -> {
                    return SenderRecord.create(topic, null, null, UUID.randomUUID().toString(), payload, 1);
                })
                .flatMap(record ->
                        sender.send(Mono.just(record))
                                .doOnError(e -> log.error("Falha no envio: {}", e.getMessage()))
                                .doOnNext(r -> log.info("Mensagem {} enviada com sucesso", r.correlationMetadata()))
                                .then()
                )
                .onErrorResume(JsonProcessingException.class, e -> {
                    log.error("Erro ao processar JSON: {}", e.getMessage());
                    return Mono.empty();
                });
    }
}
