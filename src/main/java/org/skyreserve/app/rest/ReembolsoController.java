package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.ReembolsoService;
import org.skyreserve.domain.dto.ReembolsoDTO;
import org.skyreserve.domain.entity.ReembolsoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reembolso")
@Slf4j
public class ReembolsoController {

    @Autowired
    private ReembolsoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public Mono<ReembolsoEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<ReembolsoEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ReembolsoEntity> save(@RequestBody ReembolsoEntity reembolsoEntity) {
        return service.save(new ReembolsoDTO(reembolsoEntity));
    }

    @PutMapping("/{id}")
    public Mono<ReembolsoEntity> update(@PathVariable Long id, @RequestBody ReembolsoEntity reembolsoEntity) {
        return service.update(id, new ReembolsoDTO(reembolsoEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}