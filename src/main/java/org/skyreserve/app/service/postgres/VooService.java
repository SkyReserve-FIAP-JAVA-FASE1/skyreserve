
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.PaginatedResponse;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.VooCustomRepository;
import org.skyreserve.infra.repository.postgres.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class VooService {

    @Autowired
    private VooRepository repository;

    @Autowired
    private VooCustomRepository repositoryCustom;

    public Mono<VooEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Voo não encontrado com id: " + id)));
    }

    public Flux<VooEntity> findAll() {
        return repository.findAll();
    }

    public Mono<PaginatedResponse<VooEntity>> findByVooParamters(String origem, String destino, LocalDateTime dataHoraPartidaMin, LocalDateTime dataHoraPartidaMax, int page, int size, String orderBy, String direction) {
        return repositoryCustom.findByVooParamters(origem, destino, dataHoraPartidaMin, dataHoraPartidaMax, page, size, orderBy, direction)
                .collectList()
                .zipWith(repository.count())
                .map(tuple -> {
                    List<VooEntity> content = tuple.getT1();
                    long totalElements = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) totalElements / size);
                    return new PaginatedResponse<>(content, totalPages, totalElements, page);
                });

        //return repositoryCustom.findByVooParamters(origem, destino, dataHoraPartidaMin, dataHoraPartidaMax, page, size, orderBy, direction);
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
