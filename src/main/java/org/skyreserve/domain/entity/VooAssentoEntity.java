package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.VooAssentoDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("vooassento")
public class VooAssentoEntity implements Serializable {

    @Id
    private Long id;
    private Long vooId;
    private Long assentoId;
    private String nomeAssento;
    private boolean reservado = false;
    private BigDecimal valorAssento = BigDecimal.ZERO;

    public VooAssentoEntity(VooAssentoDTO dto) {
        if (dto != null) {
            this.id = dto.getId();
            this.vooId = dto.getVooId();
            this.assentoId = dto.getAssentoId();
            this.reservado = dto.isReservado();
            this.nomeAssento = dto.getNomeAssento();
            this.valorAssento = dto.getValorAssento();
        }
    }
}

