package org.skyreserve.app.service.kafka.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.postgres.PagamentoService;
import org.skyreserve.app.service.postgres.PassageiroService;
import org.skyreserve.app.service.postgres.ReservaService;
import org.skyreserve.app.service.postgres.VooAssentoService;
import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.dto.RequisicaoReservaDTO;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
import org.skyreserve.domain.enums.TipoPagamentoEnum;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.skyreserve.infra.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class KafkaConsumerSkyTopic {

    private final KafkaReceiver<String, String> kafkaReceiver;
    private final ObjectMapper objectMapper;
    private final ReservaService reservaService;
    private final ReservaMapper reservaMapper;
    private final PassageiroService passageiroService;
    private final VooAssentoService vooAssentoService;
    private final PagamentoService pagamentoService;

    public KafkaConsumerSkyTopic(
            KafkaReceiver<String, String> kafkaReceiver,
            ObjectMapper objectMapper,
            ReservaService reservaService,
            ReservaMapper reservaMapper,
            @Value("${spring.kafka.bootstrap-servers}") String url,
            @Value("${topics.skytopic}") String topic,
            @Value("${spring.kafka.consumer.group-id}") String groupId,
            PassageiroService passageiroService,
            VooAssentoService vooAssentoService,
            PagamentoService pagamentoService) {
        this.kafkaReceiver = kafkaReceiver;
        this.objectMapper = objectMapper;
        this.reservaService = reservaService;
        this.reservaMapper = reservaMapper;
        this.passageiroService = passageiroService;
        this.vooAssentoService = vooAssentoService;
        this.pagamentoService = pagamentoService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void consumer() {
        kafkaReceiver.receive()
            .flatMap(record -> {
                String message = record.value();
                try {
                    log.info("Tópico consumido com sucesso no Kafka...");
                    RequisicaoReservaDTO requisicaoReservaDTO = objectMapper.readValue(message, new TypeReference<>() {});
                    log.info("Mapper realizado com sucesso");

                    ReservaDTO reservaDTO = ReservaDTO.builder()
                        .vooId(requisicaoReservaDTO.getIdVoo())
                        .bagagem(requisicaoReservaDTO.isBagagem())
                        .dataDaReserva(LocalDateTime.now())
                        .tipoVoo(TipoVooEnum.IDA)
                    .build();

                    return passageiroService.findByCpf(requisicaoReservaDTO.getCpf())
                            .flatMap(passageiro -> {
                                reservaDTO.setPassageiroId(passageiro.getId());
                                return vooAssentoService.findById(requisicaoReservaDTO.getAssentoSelecionado())
                                    .flatMap(vooAssentoEntity -> {
                                        reservaDTO.setAssentoId(vooAssentoEntity.getAssentoId());
                                        reservaDTO.setValorReserva(
                                                vooAssentoEntity.getValorAssento()
                                                        .add(requisicaoReservaDTO.isBagagem() ?
                                                                vooAssentoEntity.getValorBagagem() :
                                                                BigDecimal.ZERO));

                                    return reservaService.save(reservaDTO)
                                        .flatMap(reserva -> {
                                            if (!requisicaoReservaDTO.getFormaPagamento().equals(TipoPagamentoEnum.BOLETO.getDescricao())) {
                                                PagamentoDTO pagamentoDTO = PagamentoDTO.builder()
                                                    .reservaId(reserva.getId())
                                                    .valorTotal(reserva.getValorReserva())
                                                    .dataPagamento(LocalDateTime.now())
                                                    .statusPagamento(StatusPagamentoEnum.PAGO)
                                                .build();
                                                return pagamentoService.save(pagamentoDTO)
                                                    .flatMap(pgto -> {
                                                        reserva.setPagamentoId(pgto.getId());
                                                        log.info("Atualizando reserva com ID: {} e pagamento ID: {}", reserva.getId(), pgto.getId());
                                                        return reservaService.update(reserva.getId(), new ReservaDTO(reserva))
                                                            .doOnSuccess(updatedReserva -> log.info("Reserva atualizada com sucesso: {}", updatedReserva))
                                                            .doOnError(e -> log.error("Erro ao atualizar reserva: ", e));
                                                    })
                                                    .then(Mono.just(reserva));
                                                }
                                                return Mono.just(reserva);
                                            });
                                        });
                            })
                            .doOnSuccess(entity -> {
                                log.info("Reserva salva com sucesso: {}", reservaMapper.toDTO(entity));
                                record.receiverOffset().acknowledge();
                            })
                            .doOnError(error -> {
                                log.error("Erro ao salvar no banco de dados: ", error);
                                record.receiverOffset().acknowledge();
                            });
                } catch (Exception e) {
                    log.error("Erro ao processar a mensagem: ", e);
                    record.receiverOffset().acknowledge();
                    return Mono.empty();
                }
            })
            .subscribe();
    }
}
