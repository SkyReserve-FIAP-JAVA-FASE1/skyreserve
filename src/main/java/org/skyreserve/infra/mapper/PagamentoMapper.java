package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.entity.PagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {
    public PagamentoDTO toDTO(PagamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        return PagamentoDTO.builder()
                .id(entity.getId())
                .dataPagamento(entity.getDataPagamento())
                .valorTotal(entity.getValorTotal())
                .statusPagamento(entity.getStatusPagamento())
                .build();
    }

    public PagamentoEntity toEntity(PagamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        return PagamentoEntity.builder()
                .id(dto.getId())
                .dataPagamento(dto.getDataPagamento())
                .valorTotal(dto.getValorTotal())
                .statusPagamento(dto.getStatusPagamento())
                .build();
    }
}