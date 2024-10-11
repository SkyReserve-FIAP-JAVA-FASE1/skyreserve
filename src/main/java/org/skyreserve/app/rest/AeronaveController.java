package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.AeronaveService;
import org.skyreserve.domain.dto.AeronaveDTO;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/aeronave")
@Slf4j
public class AeronaveController {

    @Autowired
    private AeronaveService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public Mono<AeronaveEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<AeronaveEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AeronaveEntity> save(@RequestBody AeronaveEntity aeronaveEntity) {
        return service.save(new AeronaveDTO(aeronaveEntity));
    }

    @PutMapping("/{id}")
    public Mono<AeronaveEntity> update(@PathVariable Long id, @RequestBody AeronaveEntity aeronaveEntity) {
        return service.update(id, new AeronaveDTO(aeronaveEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}