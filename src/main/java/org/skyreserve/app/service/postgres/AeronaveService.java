
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.AeronaveDTO;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.AeronaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AeronaveService {

    @Autowired
    private AeronaveRepository repository;

    public Mono<AeronaveEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Aeronave não encontrada com id: " + id)));
    }

    public Flux<AeronaveEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<AeronaveEntity> save(AeronaveDTO obj) {
        return repository.save(new AeronaveEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Aeronave salva"))
                .doOnError(error -> log.error("Erro ao salvar aeronave: ", error));
    }

    public Mono<AeronaveEntity> update(Long id, AeronaveDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setMatricula(objDTO.getMatricula());
                    entity.setLimiteAssentos(objDTO.getLimiteAssentos());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Aeronave não encontrada com id: " + id)));
    }

}
