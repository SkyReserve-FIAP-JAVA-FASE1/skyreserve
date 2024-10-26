package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.AssentoService;
import org.skyreserve.app.service.postgres.ReservaService;
import org.skyreserve.app.service.postgres.VooAssentoService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.infra.exceptions.AssentoIsReservedException;
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

    @Autowired
    private AssentoService assentoService;

    @Autowired
    private VooAssentoService vooAssentoService;

    @GetMapping("/{id}")
    public Mono<ReservaDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(ReservaDTO::new);
    }

    @GetMapping
    public Flux<ReservaDTO> findAll() {
        return service.findAll().map(ReservaDTO::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ReservaDTO> save(@RequestBody ReservaDTO reservaDTO) {
        return vooAssentoService.isAssentoDesbloqueadoRedis(reservaDTO.getAssentoId())
                .flatMap(isDesbloqueado -> {
                    if (isDesbloqueado) {
                        return service.save(reservaDTO).map(ReservaDTO::new);
                    } else {
                        return Mono.error(new AssentoIsReservedException("O assento já está reservado: " + reservaDTO.getAssentoId()));
                    }
                })
                .doOnSuccess(savedEntity -> log.info("Reserva salva: {}", savedEntity))
                .doOnError(error -> log.error("Erro ao salvar reserva: ", error));
    }

    @PutMapping("/{id}")
    public Mono<ReservaDTO> update(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        return service.update(id, reservaDTO).map(ReservaDTO::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }


}