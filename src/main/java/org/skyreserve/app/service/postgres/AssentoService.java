package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.AssentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AssentoService {

    @Autowired
    private AssentoRepository repository;

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
                .doOnSuccess(savedEntity -> log.info("Assento salvo"))
                .doOnError(error -> log.error("Erro ao salvar inscrito: ", error));
    }

    public Mono<AssentoEntity> update(Long id, AssentoDTO objDTO) {
        return repository.findById(id)
                .flatMap(existingInscrito -> {
                    existingInscrito.setId(id);
                    existingInscrito.setDescricao(objDTO.getDescricao());
                    existingInscrito.setReservado(objDTO.isReservado());
                    return repository.save(existingInscrito);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Assento não encontrado com id: " + id)));
    }

}
