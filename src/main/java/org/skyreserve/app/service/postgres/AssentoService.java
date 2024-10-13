package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.AssentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AssentoService {

    @Autowired
    private AssentoRepository repository;

    private final List<FluxSink<AssentoEntity>> subscribers = new ArrayList<>();

    public Mono<AssentoEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Assento não encontrado com id: " + id)));
    }

    public Flux<AssentoEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<AssentoEntity> save(AssentoDTO obj) {
        return repository.save(new AssentoEntity(obj))
                .doOnSuccess(savedEntity -> {
                    notifyAssentoChanged(savedEntity);
                    log.info("Assento salvo");
                })
                .doOnError(error -> log.error("Erro ao salvar assento: ", error));
    }

    public Mono<AssentoEntity> update(Long id, AssentoDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    notifyAssentoChanged(entity);
                    entity.setId(id);
                    entity.setNome(objDTO.getNome());
                    entity.setDescricao(objDTO.getDescricao());
                    entity.setReservado(objDTO.isReservado());
                    entity.setAeronaveId(objDTO.getAeronaveId());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Assento não encontrado com id: " + id)));
    }

    public Flux<AssentoEntity> getAssentosAtualizados() {
        return Flux.create(subscriber -> {
            subscribers.add(subscriber.onDispose(() -> subscribers.remove(subscriber)));
        });
    }

    public void notifyAssentoChanged(AssentoEntity reserva) {
        for (FluxSink<AssentoEntity> subscriber : subscribers) {
            log.info("Notificação enviada: {}", new AssentoDTO(reserva).toString());
            subscriber.next(reserva);
        }
    }

}
