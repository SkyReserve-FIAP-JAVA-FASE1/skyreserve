package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.kafka.producer.KafkaProducer;
import org.skyreserve.app.service.postgres.AssentoService;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/assento")
@Slf4j
public class AssentoController {

    @Autowired
    KafkaProducer producer;

    @Autowired
    private AssentoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/start")
    public Mono<Void> start(@RequestBody AssentoDTO assentoDTO) throws IOException {
        log.info("Iniciando a aplicação.");
        String payload = objectMapper.writeValueAsString(assentoDTO);
        return producer.send(payload);
    }

    @GetMapping("/{id}")
    public Mono<AssentoEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<AssentoEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AssentoEntity> save(@RequestBody AssentoEntity assentoEntity) {
        return service.save(new AssentoDTO(assentoEntity));
    }

    @PutMapping("/{id}")
    public Mono<AssentoEntity> update(@PathVariable Long id, @RequestBody AssentoEntity assentoEntity) {
        return service.update(id, new AssentoDTO(assentoEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}