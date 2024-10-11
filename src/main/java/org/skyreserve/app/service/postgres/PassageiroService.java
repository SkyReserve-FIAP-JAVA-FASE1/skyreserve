package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.PassageiroDTO;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PassageiroService {

    @Autowired
    private PassageiroRepository repository;

    public Mono<PassageiroEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Passageiro não encontrado com id: " + id)));
    }

    public Flux<PassageiroEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<PassageiroEntity> save(PassageiroDTO obj) {
        return repository.save(new PassageiroEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Passageiro salvo"))
                .doOnError(error -> log.error("Erro ao salvar inscrito: ", error));
    }

    public Mono<PassageiroEntity> update(Long id, PassageiroDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setNome(objDTO.getNome());
                    entity.setCpf(objDTO.getCpf());
                    entity.setEmail(objDTO.getEmail());
                    entity.setNumeroPassaporte(objDTO.getNumeroPassaporte());
                    entity.setDataNascimento(objDTO.getDataNascimento());
                    entity.setCelular(objDTO.getCelular());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Passageiro não encontrado com id: " + id)));
    }

}
