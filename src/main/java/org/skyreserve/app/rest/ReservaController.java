package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.ReservaService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reserva")
@Slf4j
public class ReservaController {

    @Autowired
    private ReservaService service;

    @Autowired
    private ObjectMapper objectMapper;

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
        return service.save(new ReservaDTO(reservaEntity));
    }

    @PutMapping("/{id}")
    public Mono<ReservaEntity> update(@PathVariable Long id, @RequestBody ReservaEntity reservaEntity) {
        return service.update(id, new ReservaDTO(reservaEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}