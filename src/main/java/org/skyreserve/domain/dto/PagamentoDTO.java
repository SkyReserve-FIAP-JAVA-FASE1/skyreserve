
package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.PagamentoEntity;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
import org.skyreserve.domain.enums.TipoVooEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PagamentoDTO {

    private Long id;
    private List<Long> reservas;
    private BigDecimal valorTotal;
    private StatusPagamentoEnum statusPagamento;
    private LocalDateTime dataDaReserva;
    private boolean bagagem;
    private TipoVooEnum tipoVoo;

    public PagamentoDTO(PagamentoEntity pagamentoEntity) {
        this.id = pagamentoEntity.getId();
        this.valorTotal = pagamentoEntity.getValorTotal();
        this.statusPagamento = pagamentoEntity.getStatusPagamento();
        this.dataDaReserva = pagamentoEntity.getDataDaReserva();
        this.bagagem = pagamentoEntity.isBagagem();
        this.tipoVoo = pagamentoEntity.getTipoVoo();

        this.reservas = pagamentoEntity.getReservas();
    }

    @Override
    public String toString() {
        return "PagamentoDTO{" +
                "id=" + id +
                ", reservas=" + (reservas != null ? reservas : "null") +
                ", valorTotal=" + valorTotal +
                ", statusPagamento=" + statusPagamento +
                ", dataDaReserva=" + dataDaReserva +
                ", bagagem=" + bagagem +
                ", tipoVoo=" + tipoVoo +
                '}';
    }


}