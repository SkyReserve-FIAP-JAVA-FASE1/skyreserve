package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.RequisicaoReservaDTO;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class RequisicaoReservaMapper {
    public ReservaDTO execute(RequisicaoReservaDTO requisicao) {
        if (requisicao == null) {
            return null;
        }
        return ReservaDTO.builder()
                .passageiroId(1L)
                .vooId(1L)
                .assentoId(1L)
                .pagamentoId(null)
                .dataDaReserva(LocalDateTime.now())
                .bagagem(true)
                .tipoVoo(TipoVooEnum.IDA)
                .valorReserva(BigDecimal.ONE)
                .build();
    }
}