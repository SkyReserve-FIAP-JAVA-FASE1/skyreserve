package org.skyreserve.infra.repository.postgres;

import reactor.core.publisher.Mono;

public interface VooAssentoCustomRepository {
    Mono<Void> bloquearAssento(long assentoId);
    Mono<Void> desbloquearAssento(long assentoId);
}