package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.AssentoDTO;
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

    public Flux<AssentoEntity> findAllByAeronaveId(Long aeronaveId) {
        return repository.findAllByAeronaveId(aeronaveId);
    }

    public Flux<AssentoEntity> findAllByOrderByIdAsc() {
        return repository.findAllByOrderByIdAsc();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id)
                .doOnSuccess(deletedEntity -> log.info("Assento deletado"))
                .doOnError(error -> log.error("Erro ao deletar assento: ", error));
    }

    public Mono<AssentoEntity> save(AssentoDTO obj) {
        return repository.save(new AssentoEntity(obj))
                .doOnSuccess(savedEntity -> {
                    notifyListAssentoChanged();
                    log.info("Assento salvo");
                })
                .doOnError(error -> log.error("Erro ao salvar assento: ", error));
    }

    public Mono<AssentoEntity> update(Long id, AssentoDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setNome(objDTO.getNome());
                    entity.setDescricao(objDTO.getDescricao());
                    entity.setReservado(objDTO.isReservado());
                    entity.setAeronaveId(objDTO.getAeronaveId());
                    return repository.save(entity);
                }).doOnSuccess(entitySuccess -> {
                            log.info("Assento atualizado");
                            notifyListAssentoChanged();
                        }
                )
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Assento não encontrado com id: " + id)));
    }

    public Flux<AssentoEntity> getAssentosAtualizados() {
        return Flux.create(subscriber -> {
            subscribers.add(subscriber.onDispose(() -> subscribers.remove(subscriber)));
        });
    }

    public void notifyAssentoChanged(AssentoEntity reserva) {
        for (FluxSink<AssentoEntity> subscriber : subscribers) {
            subscriber.next(reserva);
            log.info("Notificação de um único assento enviada: {}", new AssentoDTO(reserva));
        }
    }

    public void notifyListAssentoChanged() {
        repository.findAllByOrderByIdAsc().collectList()
                .subscribe(allAssentos -> {
                    for (FluxSink<AssentoEntity> subscriber : subscribers) {
                        allAssentos.forEach(subscriber::next);
                        log.info("Notificação enviada com todos os assentos enviada.");
                    }
                });
    }

}
