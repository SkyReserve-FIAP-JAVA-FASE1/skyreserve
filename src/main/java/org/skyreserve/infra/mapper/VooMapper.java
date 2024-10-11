package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.VooEntity;
import org.springframework.stereotype.Component;

@Component
public class VooMapper {
    public VooDTO toDTO(VooEntity entity) {
        if (entity == null) {
            return null;
        }
        return VooDTO.builder()
                .id(entity.getId())
                .origem(entity.getOrigem())
                .destino(entity.getDestino())
                .dataHoraPartida(entity.getDataHoraPartida())
                .dataHoraChegada(entity.getDataHoraChegada())
                .aeronaveId(entity.getAeronaveId())
                .build();
    }

    public VooEntity toEntity(VooDTO dto) {
        if (dto == null) {
            return null;
        }
        return VooEntity.builder()
                .id(dto.getId())
                .origem(dto.getOrigem())
                .destino(dto.getDestino())
                .dataHoraPartida(dto.getDataHoraPartida())
                .dataHoraChegada(dto.getDataHoraChegada())
                .aeronaveId(dto.getAeronaveId())
                .build();
    }
}