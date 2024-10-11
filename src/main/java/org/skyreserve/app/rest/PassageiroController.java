package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.PassageiroService;
import org.skyreserve.domain.dto.PassageiroDTO;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/passageiro")
@Slf4j
public class PassageiroController {

    @Autowired
    private PassageiroService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public Mono<PassageiroEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<PassageiroEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PassageiroEntity> save(@RequestBody PassageiroEntity passageiroEntity) {
        return service.save(new PassageiroDTO(passageiroEntity));
    }

    @PutMapping("/{id}")
    public Mono<PassageiroEntity> update(@PathVariable Long id, @RequestBody PassageiroEntity passageiroEntity) {
        return service.update(id, new PassageiroDTO(passageiroEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}