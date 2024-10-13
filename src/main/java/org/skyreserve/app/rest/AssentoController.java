package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.kafka.producer.KafkaProducer;
import org.skyreserve.app.service.postgres.AssentoService;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AssentoEntity> getAssentosAtualizados() {
        return service.getAssentosAtualizados();
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