package org.skyreserve.app.service.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.SolicitarReservaService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.infra.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    SolicitarReservaService reservaService;
    @Autowired
    ReservaMapper reservaMapper;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String mensagem) throws JsonProcessingException {
        System.out.println("Mensagem recebida: " + mensagem);
        ReservaDTO payload = objectMapper.readValue(mensagem, new TypeReference<ReservaDTO>() {});
        reservaService.save(payload);
    }

}
