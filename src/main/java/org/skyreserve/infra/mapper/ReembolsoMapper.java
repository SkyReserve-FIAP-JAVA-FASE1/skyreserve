package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.ReembolsoDTO;
import org.skyreserve.domain.entity.ReembolsoEntity;
import org.springframework.stereotype.Component;

@Component
public class ReembolsoMapper {
    public ReembolsoDTO toDTO(ReembolsoEntity entity) {
        if (entity == null) {
            return null;
        }
        return ReembolsoDTO.builder()
                .id(entity.getId())
                .dataSolicitacao(entity.getDataSolicitacao())
                .dataReembolso(entity.getDataReembolso())
                .valorRestituicao(entity.getValorRestituicao())
                .reembolsoEfetuado(entity.isReembolsoEfetuado())
                .reservaId(entity.getReservaId())
                .build();
    }

    public ReembolsoEntity toEntity(ReembolsoDTO dto) {
        if (dto == null) {
            return null;
        }
        return ReembolsoEntity.builder()
                .id(dto.getId())
                .id(dto.getId())
                .dataSolicitacao(dto.getDataSolicitacao())
                .dataReembolso(dto.getDataReembolso())
                .valorRestituicao(dto.getValorRestituicao())
                .reembolsoEfetuado(dto.isReembolsoEfetuado())
                .reservaId(dto.getReservaId())
                .build();
    }
}