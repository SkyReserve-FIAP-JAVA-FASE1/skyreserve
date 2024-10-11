package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
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
@Table("pagamento")
public class PagamentoEntity implements Serializable {

    @Id
    private Long id;

    private LocalDateTime dataPagamento;
    private BigDecimal valorTotal;
    private StatusPagamentoEnum statusPagamento;

    public PagamentoEntity(PagamentoDTO dto) {
        if (dto != null) {
            this.id = dto.getId();
            this.dataPagamento = dto.getDataPagamento();
            this.valorTotal = dto.getValorTotal();
            this.statusPagamento = dto.getStatusPagamento();

        }
    }
}

