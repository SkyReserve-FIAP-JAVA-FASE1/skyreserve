package org.skyreserve.app.rest;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.kafka.producer.KafkaReactiveProducer;
import org.skyreserve.app.service.postgres.SolicitarReservaService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;

@RestController
@RequestMapping("/reserva")
@Slf4j
public class ReservaController {

    @Autowired
    private KafkaReactiveProducer producer;

    @Autowired
    private SolicitarReservaService service;

    @GetMapping("/teste")
    public void teste() {
        System.out.println("OK");
    }

    @PostMapping("/iniciar")
    public Mono<Void> start(@RequestBody ReservaDTO reservaDTO) throws IOException {
        log.info("Iniciando a aplicação.");
        return producer.send(reservaDTO);
    }

    @GetMapping("/{id}")
    public Mono<ReservaEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<ReservaEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ReservaEntity> save(@RequestBody ReservaEntity reservaEntity) {
        return service.save(reservaEntity);
    }

    @PutMapping("/{id}")
    public Mono<ReservaEntity> update(@PathVariable Long id, @RequestBody ReservaEntity reservaEntity) {
        return service.update(id, reservaEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}