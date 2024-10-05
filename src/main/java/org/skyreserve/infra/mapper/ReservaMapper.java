package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
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
                .assento(entity.getAssento().getId())
                .voo(entity.getVoo().getId())
                .pagamento(entity.getPagamento().getId())
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
                .build();
    }
}