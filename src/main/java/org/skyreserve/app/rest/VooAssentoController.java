package org.skyreserve.app.rest;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.VooAssentoService;
import org.skyreserve.domain.dto.VooAssentoDTO;
import org.skyreserve.domain.entity.VooAssentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/vooassento")
@Slf4j
public class VooAssentoController {

    @Autowired
    private VooAssentoService service;

    @GetMapping("/{id}")
    public Mono<VooAssentoDTO> findById(@PathVariable Long id) {
        return service.findById(id).map(VooAssentoDTO::new);
    }

    @GetMapping("/voo/{vooId}")
    public Flux<VooAssentoDTO> buscarVooAssentoPorVooId(@PathVariable Long vooId) {
        return service.buscarVooAssentoPorAeronave(vooId).map(VooAssentoDTO::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VooAssentoDTO> save(@RequestBody VooAssentoDTO vooAssentoDTO) {
        return service.save(vooAssentoDTO)
                .doOnSuccess(savedEntity -> {
                    service.notifyListAssentoChanged();
                    log.info("Assento reservado.");
                }).map(VooAssentoDTO::new)
                .doOnError(error -> log.error("Erro ao reservar assento: ", error));
    }

    @PutMapping("/{id}")
    public Mono<VooAssentoDTO> update(@PathVariable Long id, @RequestBody VooAssentoEntity vooAssentoEntity) {
        return service.update(id, new VooAssentoDTO(vooAssentoEntity))
                .doOnSuccess(savedEntity -> {
                    service.notifyListAssentoChanged();
                    log.info("Assento atualizado.");
                }).map(VooAssentoDTO::new)
                .doOnError(error -> log.error("Erro ao atualizar assento: ", error));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @PostMapping("/bloquear/{id}")
    public Mono<Boolean> bloquearAssento(@PathVariable Long id) {
        return service.isAssentoDesbloqueadoRedis(id)
            .flatMap(isDesbloqueado -> {
                if (isDesbloqueado) {
                    return service.bloquearAssentoRedis(id)
                        .flatMap(status ->
                            service.findById(id)
                                .flatMap(vooAssento -> {
                                    vooAssento.setReservado(true);
                                    return service.save(new VooAssentoDTO(vooAssento))
                                           .doOnSuccess(atualizarEvent -> service.notifyListAssentoChanged());
                                })
                                .thenReturn(true)
                        );
                } else {
                    return Mono.just(false);
                }
            });
    }


    @PostMapping("/desbloquear/{id}")
    public Mono<Boolean> desbloquearAssento(@PathVariable Long id) {
        return service.desbloquearAssentoRedis(id)
            .flatMap(desbloqueado -> {
                return service.desbloquearAssentoRedis(id)
                        .flatMap(status ->
                        service.findById(id)
                            .flatMap(vooAssento -> {
                                vooAssento.setReservado(false);
                                return service.save(new VooAssentoDTO(vooAssento))
                                       .doOnSuccess(atualizarEvent -> service.notifyListAssentoChanged());
                            })
                            .thenReturn(false)
                    );
            });
    }

    @GetMapping("/estado/{id}")
    public Mono<Boolean> isAssentoDesbloqueado(@PathVariable Long id) {
        return service.isAssentoDesbloqueadoRedis(id);
    }

    @GetMapping(value = "/stream/{vooid}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<VooAssentoDTO> getAssentosAtualizadosPeloVoo(@PathVariable Long vooid) {
        return service.getAssentosAtualizados().filter(vooAssentoEntity ->
                vooAssentoEntity.getVooId().equals(vooid)).map(VooAssentoDTO::new);
    }


}