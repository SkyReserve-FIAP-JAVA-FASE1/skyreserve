
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class VooService {

    @Autowired
    private VooRepository repository;

    public Mono<VooEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Voo não encontrado com id: " + id)));
    }

    public Flux<VooEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<VooEntity> save(VooDTO obj) {
        return repository.save(new VooEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Voo salva"))
                .doOnError(error -> log.error("Erro ao salvar voo: ", error));
    }

    public Mono<VooEntity> update(Long id, VooDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setOrigem(entity.getOrigem());
                    entity.setDestino(entity.getDestino());
                    entity.setDataHoraPartida(entity.getDataHoraPartida());
                    entity.setDataHoraChegada(entity.getDataHoraChegada());
                    entity.setAeronaveId(entity.getAeronaveId());

                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Voo não encontrado com id: " + id)));
    }

}
