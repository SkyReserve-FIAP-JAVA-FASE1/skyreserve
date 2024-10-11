package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.springframework.stereotype.Component;

@Component
public class AssentoMapper {
    public AssentoDTO toDTO(AssentoEntity entity) {
        return AssentoDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .reservado(entity.isReservado())
                .aeronaveId(entity.getAeronaveId())
                .build();
    }

    public AssentoEntity toEntity(AssentoDTO dto) {
        return AssentoEntity.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .reservado(dto.isReservado())
                .aeronaveId(dto.getAeronaveId())
                .build();
    }
}