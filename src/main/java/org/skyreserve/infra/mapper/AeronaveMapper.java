package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.AeronaveDTO;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.springframework.stereotype.Component;

@Component
public class AeronaveMapper {
    public AeronaveDTO toDTO(AeronaveEntity entity) {
        if (entity == null) {
            return null;
        }
        return AeronaveDTO.builder()
                .id(entity.getId())
                .matricula(entity.getMatricula())
                .limiteAssentos(entity.getLimiteAssentos())
                .build();
    }

    public AeronaveEntity toEntity(AeronaveDTO dto) {
        if (dto == null) {
            return null;
        }
        return AeronaveEntity.builder()
                .id(dto.getId())
                .matricula(dto.getMatricula())
                .limiteAssentos(dto.getLimiteAssentos())
                .build();
    }
}