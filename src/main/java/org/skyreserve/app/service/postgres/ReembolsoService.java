
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.ReembolsoDTO;
import org.skyreserve.domain.entity.ReembolsoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.ReembolsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReembolsoService {

    @Autowired
    private ReembolsoRepository repository;

    public Mono<ReembolsoEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reembolso não encontrado com id: " + id)));
    }

    public Flux<ReembolsoEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<ReembolsoEntity> save(ReembolsoDTO obj) {
        return repository.save(new ReembolsoEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Reembolso salvo"))
                .doOnError(error -> log.error("Erro ao salvar reembolso: ", error));
    }

    public Mono<ReembolsoEntity> update(Long id, ReembolsoDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setDataReembolso(objDTO.getDataReembolso());
                    entity.setValorRestituicao(objDTO.getValorRestituicao());
                    entity.setReembolsoEfetuado(objDTO.isReembolsoEfetuado());
                    entity.setDataSolicitacao(objDTO.getDataSolicitacao());
                    entity.setReservaId(objDTO.getReservaId());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reembolso não encontrado com id: " + id)));
    }

}
