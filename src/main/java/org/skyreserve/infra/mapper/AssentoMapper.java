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
                .nome(entity.getNome())
                .reservado(entity.isReservado())
                .aeronaveId(entity.getAeronaveId())
                .build();
    }

    public AssentoEntity toEntity(AssentoDTO dto) {
        return AssentoEntity.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .reservado(dto.isReservado())
                .aeronaveId(dto.getAeronaveId())
                .build();
    }
}