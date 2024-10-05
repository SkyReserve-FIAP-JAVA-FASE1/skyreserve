package org.skyreserve.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReembolsoDTO {

    private Long id;
    private ReservaDTO reserva;
    private BigDecimal valorRestituicao;
    private boolean reembolsoEfetuado;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataReembolso;

    @Override
    public String toString() {
        return "ReembolsoDTO{" +
                "id=" + id +
                ", reserva=" + (reserva != null ? reserva.getId() : "null") +
                ", valorRestituicao=" + valorRestituicao +
                ", reembolsoEfetuado=" + reembolsoEfetuado +
                ", dataSolicitacao=" + dataSolicitacao +
                ", dataReembolso=" + dataReembolso +
                '}';
    }

}