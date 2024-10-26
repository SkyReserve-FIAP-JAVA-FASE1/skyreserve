package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.ReembolsoService;
import org.skyreserve.domain.dto.ReembolsoDTO;
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
    public Mono<ReembolsoDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(ReembolsoDTO::new);
    }

    @GetMapping
    public Flux<ReembolsoDTO> findAll() {
        return service.findAll().map(ReembolsoDTO::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ReembolsoDTO> save(@RequestBody ReembolsoDTO reembolsoDTO) {
        return service.save(reembolsoDTO).map(ReembolsoDTO::new);
    }

    @PutMapping("/{id}")
    public Mono<ReembolsoDTO> update(@PathVariable Long id, @RequestBody ReembolsoDTO reembolsoDTO) {
        return service.update(id, reembolsoDTO).map(ReembolsoDTO::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}