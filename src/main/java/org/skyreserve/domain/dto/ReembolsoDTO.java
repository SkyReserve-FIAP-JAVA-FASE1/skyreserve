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
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataReembolso;
    private BigDecimal valorRestituicao;
    private boolean reembolsoEfetuado;
    private Long reservaId;

    public ReembolsoDTO(ReembolsoEntity entity) {
        this.id = entity.getId();
        this.dataSolicitacao = entity.getDataSolicitacao();
        this.dataReembolso = entity.getDataReembolso();
        this.valorRestituicao = entity.getValorRestituicao();
        this.reembolsoEfetuado = entity.isReembolsoEfetuado();
        this.reservaId = entity.getReservaId();
    }


    @Override
    public String toString() {
        return "PagamentoEntity{" +
                "id=" + id +
                ", dataSolicitacao='" + dataSolicitacao + '\'' +
                ", dataReembolso='" + dataReembolso + '\'' +
                ", valorRestituicao=" + valorRestituicao +
                ", reembolsoEfetuado=" + reembolsoEfetuado +
                ", reservaId=" + reservaId +
                '}';
    }


}