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
    public Mono<VooAssentoEntity> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/voo/{vooId}")
    public Flux<VooAssentoEntity> buscarVooAssentoPorVooId(@PathVariable Long vooId) {
        return service.buscarVooAssentoPorAeronave(vooId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VooAssentoEntity> save(@RequestBody VooAssentoEntity vooAssentoEntity) {
        return service.save(new VooAssentoDTO(vooAssentoEntity))
                .doOnSuccess(savedEntity -> {
                    service.notifyListAssentoChanged();
                    log.info("Assento reservado.");
                })
                .doOnError(error -> log.error("Erro ao reservar assento: ", error));
    }

    @PutMapping("/{id}")
    public Mono<VooAssentoEntity> update(@PathVariable Long id, @RequestBody VooAssentoEntity vooAssentoEntity) {
        return service.update(id, new VooAssentoDTO(vooAssentoEntity))
                .doOnSuccess(savedEntity -> {
                    service.notifyListAssentoChanged();
                    log.info("Assento atualizado.");
                })
                .doOnError(error -> log.error("Erro ao atualizar assento: ", error));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @PostMapping("/bloquear/{id}")
    public Mono<Boolean> bloquearAssento(@PathVariable String id) {
        return service.isAssentoDesbloqueado(id)
            .flatMap(isDesbloqueado -> {
                if (isDesbloqueado) {
                    return service.bloquearAssento(id);
                } else {
                    return Mono.just(false);
                }
            });
    }

    @PostMapping("/desbloquear/{id}")
    public Mono<Boolean> desbloquearAssento(@PathVariable String id) {
        return service.desbloquearAssento(id);
    }

    @GetMapping("/estado/{id}")
    public Mono<Boolean> isAssentoDesbloqueado(@PathVariable String id) {
        return service.isAssentoDesbloqueado(id);
    }

    @GetMapping(value = "/stream/{vooid}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<VooAssentoEntity> getAssentosAtualizadosPeloVoo(@PathVariable Long vooid) {
        return service.getAssentosAtualizados().filter(vooAssentoEntity ->
                vooAssentoEntity.getVooId().equals(vooid));
    }




}