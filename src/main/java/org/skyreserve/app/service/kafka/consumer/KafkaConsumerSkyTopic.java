package org.skyreserve.app.service.kafka.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.ReservaService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.infra.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Service
@Slf4j
public class KafkaConsumerSkyTopic {

    private final KafkaReceiver<String, String> kafkaReceiver;
    private final ObjectMapper objectMapper;
    private final ReservaService service;
    private final ReservaMapper reservaMapper;

    public KafkaConsumerSkyTopic(
            KafkaReceiver<String, String> kafkaReceiver,
            ObjectMapper objectMapper,
            ReservaService service, ReservaMapper reservaMapper,
            @Value("${spring.kafka.bootstrap-servers}") String url,
            @Value("${topics.skytopic}") String topic,
            @Value("${spring.kafka.consumer.group-id}") String groupId) {
        this.kafkaReceiver = kafkaReceiver;
        this.objectMapper = objectMapper;
        this.service = service;
        this.reservaMapper = reservaMapper;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void consumer() {
        kafkaReceiver.receive().flatMap(record -> {
            String message = record.value();
            try {
                log.info("Tópico consumido com sucesso no Kafka...");

                ReservaDTO reservaDTO = objectMapper.readValue(message, new TypeReference<>() {
                });
                log.info("Mapper realizado com sucesso");

                return service.save(reservaDTO).doOnSuccess(entity -> {
                    log.info("Reserva salva com sucesso: {}", reservaMapper.toDTO(entity).toString());
                    record.receiverOffset().acknowledge();
                }).doOnError(error -> log.error("Erro ao salvar no banco de dados: ", error));

            } catch (Exception e) {
                log.error("Erro ao processar a mensagem: ", e);
                record.receiverOffset().acknowledge();
                return Mono.empty();
            }
        }).subscribe();
    }


}
