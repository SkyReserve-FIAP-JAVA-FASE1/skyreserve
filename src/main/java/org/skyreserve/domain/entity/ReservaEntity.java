package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("reserva")
public class ReservaEntity implements Serializable {

    @Id
    private Long id;

    private Long passageiroId;
    private Long vooId;
    private Long assentoId;
    private Long pagamentoId;
    private LocalDateTime dataDaReserva;
    private boolean bagagem = false;
    private TipoVooEnum tipoVoo;
    private BigDecimal valorReserva;

    public ReservaEntity(ReservaDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.passageiroId = dto.getPassageiroId();
            this.vooId = dto.getVooId();
            this.assentoId = dto.getAssentoId();
            this.pagamentoId = dto.getPagamentoId();
            this.dataDaReserva = dto.getDataDaReserva();
            this.bagagem = dto.isBagagem();
            this.tipoVoo = dto.getTipoVoo();
            this.valorReserva = dto.getValorReserva();
        }
    }

}