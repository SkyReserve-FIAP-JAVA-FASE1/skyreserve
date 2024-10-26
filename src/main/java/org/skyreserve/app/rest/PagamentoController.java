package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.PagamentoService;
import org.skyreserve.domain.dto.PagamentoDTO;
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
    public Mono<PagamentoDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(PagamentoDTO::new);
    }

    @GetMapping
    public Flux<PagamentoDTO> findAll() {
        return service.findAll().map(PagamentoDTO::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PagamentoDTO> save(@RequestBody PagamentoDTO pagamentoDTO) {
        return service.save(pagamentoDTO).map(PagamentoDTO::new);
    }

    @PutMapping("/{id}")
    public Mono<PagamentoDTO> update(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        return service.update(id, pagamentoDTO).map(PagamentoDTO::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}