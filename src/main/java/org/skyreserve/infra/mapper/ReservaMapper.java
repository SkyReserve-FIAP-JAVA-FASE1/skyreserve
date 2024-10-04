package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.*;
import org.skyreserve.domain.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public ReservaDTO toDTO(ReservaEntity entity) {
        return ReservaDTO.builder()
                .id(entity.getId())
                .passageiro(entity.getPassageiro())
                .dataDaReserva(entity.getDataDaReserva())
                .bagagem(entity.isBagagem())
                .tipoVoo(entity.getTipoVoo())
                .valorReserva(entity.getValorReserva())
                .assento(AssentoDTO.builder()
                        .id(entity.getAssento().getId())
                        .reservado(entity.getAssento().isReservado())
                        .descricao(entity.getAssento().getDescricao())
                        .build())
                .voo(VooDTO.builder()
                        .id(entity.getVoo().getId())
                        .origem(entity.getVoo().getOrigem())
                        .destino(entity.getVoo().getDestino())
                        .dataHoraPartida(entity.getVoo().getDataHoraPartida())
                        .dataHoraChegada(entity.getVoo().getDataHoraChegada())
                        .aeronave(AeronaveDTO.builder()
                                .id(entity.getVoo().getAeronave().getId())
                                .matricula(entity.getVoo().getAeronave().getMatricula())
                                .build())
                        .build())
                .pagamento(PagamentoDTO.builder()
                        .id(entity.getPagamento().getId())
                        .valorTotal(entity.getPagamento().getValorTotal())
                        .valorReserva(entity.getPagamento().getValorReserva())
                        .bagagem(entity.getPagamento().isBagagem())
                        .dataDaReserva(entity.getPagamento().getDataDaReserva())
                        .statusPagamento(entity.getPagamento().getStatusPagamento())
                        .tipoVoo(entity.getPagamento().getTipoVoo()).build())
                .build();
    }

    public ReservaEntity toEntity(ReservaDTO dto) {
        return ReservaEntity.builder()
                .id(dto.getId())
                .passageiro(dto.getPassageiro())
                .dataDaReserva(dto.getDataDaReserva())
                .bagagem(dto.isBagagem())
                .tipoVoo(dto.getTipoVoo())
                .valorReserva(dto.getValorReserva())
                .assento(AssentoEntity.builder()
                        .id(dto.getAssento().getId())
                        .reservado(dto.getAssento().isReservado())
                        .descricao(dto.getAssento().getDescricao())
                        .build())
                .voo(VooEntity.builder()
                        .id(dto.getVoo().getId())
                        .origem(dto.getVoo().getOrigem())
                        .destino(dto.getVoo().getDestino())
                        .dataHoraPartida(dto.getVoo().getDataHoraPartida())
                        .dataHoraChegada(dto.getVoo().getDataHoraChegada())
                        .aeronave(AeronaveEntity.builder()
                                .id(dto.getVoo().getAeronave().getId())
                                .matricula(dto.getVoo().getAeronave().getMatricula())
                                .build())
                        .build())
                .pagamento(PagamentoEntity.builder()
                        .id(dto.getPagamento().getId())
                        .valorTotal(dto.getPagamento().getValorTotal())
                        .valorReserva(dto.getPagamento().getValorReserva())
                        .bagagem(dto.getPagamento().isBagagem())
                        .dataDaReserva(dto.getPagamento().getDataDaReserva())
                        .statusPagamento(dto.getPagamento().getStatusPagamento())
                        .tipoVoo(dto.getPagamento().getTipoVoo()).build())
                .build();
    }
}