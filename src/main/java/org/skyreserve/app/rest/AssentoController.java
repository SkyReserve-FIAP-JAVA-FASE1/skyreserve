package org.skyreserve.app.rest;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.AssentoService;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assento")
@Slf4j
public class AssentoController {

    @Autowired
    private AssentoService service;

    @GetMapping("/{id}")
    public Mono<AssentoEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/aeronave/{aeronaveid}")
    public Flux<AssentoEntity> findAllByAeronaveId(@PathVariable Long aeronaveid) {
        return service.findAllByAeronaveId(aeronaveid);
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