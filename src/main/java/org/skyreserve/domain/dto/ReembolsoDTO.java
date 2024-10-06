package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.ReembolsoEntity;

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
    private Long idReserva;
    private BigDecimal valorRestituicao;
    private boolean reembolsoEfetuado;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataReembolso;

    public ReembolsoDTO(ReembolsoEntity reembolsoEntity) {
        this.id = reembolsoEntity.getId();
        this.idReserva = reembolsoEntity.getIdReserva();
        this.valorRestituicao = reembolsoEntity.getValorRestituicao();
        this.reembolsoEfetuado = reembolsoEntity.isReembolsoEfetuado();
        this.dataSolicitacao = reembolsoEntity.getDataSolicitacao();
        this.dataReembolso = reembolsoEntity.getDataReembolso();
    }

    @Override
    public String toString() {
        return "ReembolsoDTO{" +
                "id=" + id +
                ", idReserva=" + idReserva +
                ", valorRestituicao=" + valorRestituicao +
                ", reembolsoEfetuado=" + reembolsoEfetuado +
                ", dataSolicitacao=" + dataSolicitacao +
                ", dataReembolso=" + dataReembolso +
                '}';
    }

}