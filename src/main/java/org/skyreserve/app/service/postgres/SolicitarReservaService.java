package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.domain.entity.ReservaEntity;
import org.skyreserve.infra.repository.postgres.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SolicitarReservaService {

        @Autowired
        private ReservaRepository repository;

        public Flux<ReservaEntity> findAll() {
            return repository.findAll();
        }

        public Mono<ReservaEntity> findById(Long id) {
            return repository.findById(id)
                    .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reserva não encontrada com id: " + id)));
        }

    public Mono<ReservaEntity> save(ReservaEntity reservaEntity) {
        return repository.save(reservaEntity)
                .doOnSuccess(savedEntity -> log.info("Reserva salva"))
                .doOnError(error -> log.error("Erro ao salvar reserva: ", error));
    }

        public Mono<ReservaEntity> update(Long id, ReservaEntity updatedReservaEntity) {
            return repository.findById(id)
                    .flatMap(existingReserva -> {
                        existingReserva.setPassageiro(updatedReservaEntity.getPassageiro());
                        existingReserva.setVoo(updatedReservaEntity.getVoo());
                        existingReserva.setAssento(updatedReservaEntity.getAssento());
                        existingReserva.setDataDaReserva(updatedReservaEntity.getDataDaReserva());
                        existingReserva.setBagagem(updatedReservaEntity.isBagagem());
                        existingReserva.setTipoVoo(updatedReservaEntity.getTipoVoo());
                        existingReserva.setPagamento(updatedReservaEntity.getPagamento());
                        existingReserva.setValorReserva(updatedReservaEntity.getValorReserva());
                        return repository.save(existingReserva);
                    })
                    .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reserva não encontrada com id: " + id)));
        }

        public Mono<Void> deleteById(Long id) {
            return repository.deleteById(id);
        }
}
