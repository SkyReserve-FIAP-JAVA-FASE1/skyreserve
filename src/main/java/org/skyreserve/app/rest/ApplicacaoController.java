package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.kafka.producer.KafkaProducer;
import org.skyreserve.domain.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.io.IOException;

@RestController
@RequestMapping("/")
@Slf4j
public class ApplicacaoController {

    @Autowired
    KafkaProducer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${topics.skytopic}")
    private String topic;

    @PostMapping("/start")
    public Mono<Void> start(@RequestBody ReservaDTO reservaDTO) throws IOException {
        log.info("Iniciando a aplicação.");
        String payload = objectMapper.writeValueAsString(reservaDTO);
        return producer.send(topic, payload);
    }

}