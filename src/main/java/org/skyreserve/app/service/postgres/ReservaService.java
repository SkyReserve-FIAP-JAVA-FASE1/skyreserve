
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public Mono<ReservaEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reserva não encontrado com id: " + id)));
    }

    public Flux<ReservaEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<ReservaEntity> save(ReservaDTO obj) {
        return repository.save(new ReservaEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Reserva salva"))
                .doOnError(error -> log.error("Erro ao salvar a reserva: ", error));
    }

    public Mono<ReservaEntity> update(Long id, ReservaDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);

                    entity.setPassageiroId(objDTO.getPassageiroId());
                    entity.setVooId(objDTO.getVooId());
                    entity.setAssentoId(objDTO.getAssentoId());
                    entity.setPagamentoId(objDTO.getPagamentoId());
                    entity.setDataDaReserva(objDTO.getDataDaReserva());
                    entity.setBagagem(objDTO.isBagagem());
                    entity.setTipoVoo(objDTO.getTipoVoo());
                    entity.setValorReserva(objDTO.getValorReserva());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reserva não encontrada com id: " + id)));
    }

}
