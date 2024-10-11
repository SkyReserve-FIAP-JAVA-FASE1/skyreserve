package org.skyreserve.app.service.kafka.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.ReservaService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.infra.config.kafka.CreateReceiverOptions;
import org.skyreserve.infra.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class KafkaConsumerSkyTopic {

    private final Flux<?> kafkaFlux;

    public KafkaConsumerSkyTopic(ReceiverOptions<String, String> receiverOptions,
                                 @Value("${spring.kafka.bootstrap-servers}") String url,
                                 @Value("${topics.skytopic}") String topic,
                                 @Value("${spring.kafka.consumer.group-id}") String groupId,
                                 ObjectMapper objectMapper,
                                 ReservaService service,
                                 ReservaMapper reservaMapper, CreateReceiverOptions createReceiverOptions) {

        receiverOptions = createReceiverOptions.create(topic, groupId, url);
        KafkaReceiver<String, String> kafkaReceiver = KafkaReceiver.create(receiverOptions);

        kafkaFlux = kafkaReceiver.receive()
                .flatMap(record -> {
                    String message = record.value();
                    try {
                        log.info("Tópico consumido com sucesso no Kafka...");

                        ReservaDTO reservaDTO = objectMapper.readValue(message, new TypeReference<>() {
                        });
                        log.info("Mapper realizado com sucesso");

                        return service.save(reservaDTO)
                                .doOnSuccess(entity -> {
                                    log.info("Reserva salva com sucesso: {}", reservaMapper.toDTO(entity).toString());
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
