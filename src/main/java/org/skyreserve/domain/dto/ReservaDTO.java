package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.ReservaEntity;
import org.skyreserve.domain.enums.TipoVooEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReservaDTO {

    private Long id;
    private Long passageiroId;
    private Long vooId;
    private Long assentoId;
    private Long pagamentoId;
    private LocalDateTime dataDaReserva;
    private boolean bagagem = false;
    private TipoVooEnum tipoVoo;
    private BigDecimal valorReserva;

    public ReservaDTO(ReservaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.passageiroId = entity.getPassageiroId();
            this.vooId = entity.getVooId();
            this.assentoId = entity.getAssentoId();
            this.pagamentoId = entity.getPagamentoId();
            this.dataDaReserva = entity.getDataDaReserva();
            this.bagagem = entity.isBagagem();
            this.tipoVoo = entity.getTipoVoo();
            this.valorReserva = entity.getValorReserva();
        }
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id=" + id +
                ", passageiroId=" + passageiroId +
                ", vooId=" + vooId +
                ", assentoId=" + assentoId +
                ", pagamentoId=" + pagamentoId +
                ", dataDaReserva=" + dataDaReserva +
                ", bagagem=" + bagagem +
                ", tipoVoo=" + tipoVoo +
                ", valorReserva=" + valorReserva +
                '}';
    }


}