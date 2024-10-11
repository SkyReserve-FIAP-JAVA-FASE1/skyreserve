package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.PagamentoEntity;
import org.skyreserve.domain.enums.StatusPagamentoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PagamentoDTO {

    private Long id;
    private LocalDateTime dataPagamento;
    private BigDecimal valorTotal;
    private StatusPagamentoEnum statusPagamento;

    public PagamentoDTO(PagamentoEntity entity) {
        this.id = entity.getId();
        this.dataPagamento = entity.getDataPagamento();
        this.valorTotal = entity.getValorTotal();
        this.statusPagamento = entity.getStatusPagamento();
    }


    @Override
    public String toString() {
        return "PagamentoEntity{" +
                "id=" + id +
                ", dataPagamento='" + dataPagamento + '\'' +
                ", valorTotal='" + valorTotal + '\'' +
                ", statusPagamento=" + statusPagamento +
                '}';
    }


}