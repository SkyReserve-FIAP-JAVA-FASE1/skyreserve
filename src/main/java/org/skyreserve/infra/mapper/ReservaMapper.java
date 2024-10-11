package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {
    public ReservaDTO toDTO(ReservaEntity entity) {
        if (entity == null) {
            return null;
        }
        return ReservaDTO.builder()
                .id(entity.getId())
                .passageiroId(entity.getId())
                .vooId(entity.getId())
                .assentoId(entity.getId())
                .pagamentoId(entity.getPagamentoId())
                .dataDaReserva(entity.getDataDaReserva())
                .bagagem(entity.isBagagem())
                .tipoVoo(entity.getTipoVoo())
                .valorReserva(entity.getValorReserva())
                .build();
    }

    public ReservaEntity toEntity(ReservaDTO dto) {
        if (dto == null) {
            return null;
        }
        return ReservaEntity.builder()
                .id(dto.getId())
                .passageiroId(dto.getId())
                .vooId(dto.getId())
                .assentoId(dto.getId())
                .pagamentoId(dto.getPagamentoId())
                .dataDaReserva(dto.getDataDaReserva())
                .bagagem(dto.isBagagem())
                .tipoVoo(dto.getTipoVoo())
                .valorReserva(dto.getValorReserva())
                .build();
    }
}