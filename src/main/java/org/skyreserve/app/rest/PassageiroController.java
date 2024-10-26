package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.PassageiroService;
import org.skyreserve.domain.dto.PassageiroDTO;
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
    public Mono<PassageiroDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(PassageiroDTO::new);
    }

    @GetMapping("/cpf/{cpf}")
    public Mono<PassageiroDTO> findByCpf(@PathVariable String cpf) {
        return service.findByCpf(cpf).map(PassageiroDTO::new);
    }

    @GetMapping
    public Flux<PassageiroDTO> findAll() {
        return service.findAll().map(PassageiroDTO::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PassageiroDTO> save(@RequestBody PassageiroDTO passageiroDTO) {
        return service.save(passageiroDTO).map(PassageiroDTO::new);
    }

    @PutMapping("/{id}")
    public Mono<PassageiroDTO> update(@PathVariable Long id, @RequestBody PassageiroDTO passageiroDTO) {
        return service.update(id, passageiroDTO).map(PassageiroDTO::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}