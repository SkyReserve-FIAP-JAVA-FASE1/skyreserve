package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.PagamentoService;
import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.entity.PagamentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pagamento")
@Slf4j
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public Mono<PagamentoEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Flux<PagamentoEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PagamentoEntity> save(@RequestBody PagamentoEntity pagamentoEntity) {
        return service.save(new PagamentoDTO(pagamentoEntity));
    }

    @PutMapping("/{id}")
    public Mono<PagamentoEntity> update(@PathVariable Long id, @RequestBody PagamentoEntity pagamentoEntity) {
        return service.update(id, new PagamentoDTO(pagamentoEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}