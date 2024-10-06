package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.*;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public ReservaDTO toDTO(ReservaEntity entity) {
        return ReservaDTO.builder()
                .id(entity.getId())
                .passageiroDTO(new PassageiroDTO(entity.getPassageiro()))
                .dataDaReserva(entity.getDataDaReserva())
                .bagagem(entity.isBagagem())
                .tipoVoo(entity.getTipoVoo())
                .valorReserva(entity.getValorReserva())
                .assentoDTO(new AssentoDTO(entity.getAssento()))
                .vooDTO(new VooDTO(entity.getVoo()))
                .pagamento(new PagamentoDTO(entity.getPagamento()))
                .build();
    }

    public ReservaEntity toEntity(ReservaDTO dto) {
        return ReservaEntity.builder()
                .id(dto.getId())
                .passageiro(new PassageiroEntity(dto.getPassageiroDTO()))
                .dataDaReserva(dto.getDataDaReserva())
                .bagagem(dto.isBagagem())
                .tipoVoo(dto.getTipoVoo())
                .valorReserva(dto.getValorReserva())
                .build();
    }
}