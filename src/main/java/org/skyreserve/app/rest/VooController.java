package org.skyreserve.app.rest;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.VooService;
import org.skyreserve.domain.dto.PaginatedResponse;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.VooEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/voo")
@Slf4j
public class VooController {

    @Autowired
    private VooService service;

    @GetMapping("/{id}")
    public Mono<VooEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/search")
    public Mono<PaginatedResponse<VooEntity>> search(
            @RequestParam String origem,
            @RequestParam String destino,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraPartidaMin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraPartidaMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            String orderBy,
            String direction) {
        return service.findByVooParamters(origem, destino, dataHoraPartidaMin, dataHoraPartidaMax, page, size, orderBy, direction);
    }

    @GetMapping
    public Flux<VooEntity> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VooEntity> save(@RequestBody VooEntity vooEntity) {
        return service.save(new VooDTO(vooEntity));
    }

    @PutMapping("/{id}")
    public Mono<VooEntity> update(@PathVariable Long id, @RequestBody VooEntity vooEntity) {
        return service.update(id, new VooDTO(vooEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}