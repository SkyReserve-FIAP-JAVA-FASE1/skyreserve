
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.entity.PagamentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Mono<PagamentoEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Pagamento não encontrado com id: " + id)));
    }

    public Flux<PagamentoEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<PagamentoEntity> save(PagamentoDTO obj) {
        return repository.save(new PagamentoEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Pagamento salvo"))
                .doOnError(error -> log.error("Erro ao salvar pagamento: ", error));
    }

    public Mono<PagamentoEntity> update(Long id, PagamentoDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setDataPagamento(objDTO.getDataPagamento());
                    entity.setValorTotal(objDTO.getValorTotal());
                    entity.setStatusPagamento(objDTO.getStatusPagamento());
                    entity.setReservaId(objDTO.getReservaId());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Pagamento não encontrado com id: " + id)));
    }

}
