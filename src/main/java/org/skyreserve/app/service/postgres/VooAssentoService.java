
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.VooAssentoDTO;
import org.skyreserve.domain.entity.VooAssentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.VooAssentoCustomRepository;
import org.skyreserve.infra.repository.postgres.VooAssentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VooAssentoService {

    @Autowired
    private VooAssentoRepository repository;

    @Autowired
    private VooAssentoCustomRepository vooAssentoCustomRepository;

    private final List<FluxSink<VooAssentoEntity>> subscribers = new ArrayList<>();
    private static final String ASSENTO_PREFIX = "voo_assento:";  // Prefixo para as chaves dos assentos
    private final ReactiveValueOperations<String, Boolean> valueOperations;

    public VooAssentoService(ReactiveRedisTemplate<String, Boolean> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Flux<VooAssentoEntity> getAssentosAtualizados() {
        return Flux.create(subscriber -> {
            subscribers.add(subscriber.onDispose(() -> subscribers.remove(subscriber)));
        });
    }

    public void notifyAssentoChanged(VooAssentoEntity vooAssentoEntity) {
        for (FluxSink<VooAssentoEntity> subscriber : subscribers) {
            subscriber.next(vooAssentoEntity);
            log.info("Notificação do assento enviada: {}", new VooAssentoDTO(vooAssentoEntity));
        }
    }

    public void notifyListAssentoChanged() {
        repository.findAllByOrderByIdAsc().collectList()
                .subscribe(allAssentos -> {
                    for (FluxSink<VooAssentoEntity> subscriber : subscribers) {
                        allAssentos.forEach(subscriber::next);
                        log.info("Notificação enviada com todos os assentos enviada.");
                    }
                });
    }

    public Mono<VooAssentoEntity> findById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Voo não encontrado com id: " + id)));
    }

    public Flux<VooAssentoEntity> findAll() {
        return repository.findAll();
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Mono<VooAssentoEntity> save(VooAssentoDTO obj) {
        return repository.save(new VooAssentoEntity(obj))
                .doOnSuccess(savedEntity -> log.info("Voo assento reservado"))
                .doOnError(error -> log.error("Erro ao reservar voo e assento: ", error));
    }

    public Mono<VooAssentoEntity> update(Long id, VooAssentoDTO objDTO) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setId(id);
                    entity.setVooId(objDTO.getVooId());
                    entity.setReservado(objDTO.isReservado());
                    entity.setAssentoId(objDTO.getAssentoId());
                    entity.setNomeAssento(objDTO.getNomeAssento());
                    entity.setValorAssento(objDTO.getValorAssento());
                    entity.setValorBagagem(objDTO.getValorBagagem());
                    return repository.save(entity);
                })
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Reserva de Voo assento não encontrado com id: " + id)));
    }


    public Mono<Boolean> bloquearAssentoRedis(Long vooAssentoId) {
        String key = ASSENTO_PREFIX + vooAssentoId;
        return valueOperations.set(key, true);
    }

    public Mono<Boolean> desbloquearAssentoRedis(Long assentoId) {
        String key = ASSENTO_PREFIX + assentoId;
        return valueOperations.delete(key).map(result -> result != null && result);
    }

    public Mono<Boolean> isAssentoDesbloqueadoRedis(long vooAssentoId) {
        String key = ASSENTO_PREFIX + vooAssentoId;
        return valueOperations.get(key).defaultIfEmpty(false).map(isBlocked -> !isBlocked);
    }

    public Mono<Void> bloquearAssentoBanco(long assentoId) {
        return vooAssentoCustomRepository.bloquearAssento(assentoId);
    }

    public Mono<Void> desbloquearAssentoBanco(long assentoId) {
        return vooAssentoCustomRepository.desbloquearAssento(assentoId);
    }


    public Flux<VooAssentoEntity> buscarVooAssentoPorAeronave(Long vooId) {
        return repository.findAllByVooIdOrderByNomeAssentoAsc(vooId)
                .switchIfEmpty(Mono.error(new ObjectNotFoundException("Voo Assento não encontrado com vooId: " + vooId)));
    }
}
