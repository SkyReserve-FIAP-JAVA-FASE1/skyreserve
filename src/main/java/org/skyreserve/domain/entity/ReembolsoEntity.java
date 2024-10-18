package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.ReembolsoDTO;
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
@Table("reembolso")
public class ReembolsoEntity implements Serializable {

    @Id
    private Long id;

    private BigDecimal valorRestituicao;
    private boolean reembolsoEfetuado = false;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataReembolso;
    private Long reservaId;

    public ReembolsoEntity(ReembolsoDTO dto) {
        if (dto != null) {
            this.id = dto.getId();
            this.valorRestituicao = dto.getValorRestituicao();
            this.reembolsoEfetuado = dto.isReembolsoEfetuado();
            this.dataSolicitacao = dto.getDataSolicitacao();
            this.dataReembolso = dto.getDataReembolso();
            this.reservaId = dto.getReservaId();

        }
    }
}

