package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.VooAssentoEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VooAssentoDTO {

    private Long id;
    private Long vooId;
    private Long assentoId;
    private String nomeAssento;
    private boolean reservado = false;
    private BigDecimal valorAssento = BigDecimal.ZERO;
    private BigDecimal valorBagagem = BigDecimal.ZERO;

    public VooAssentoDTO(VooAssentoEntity entity) {
        id = entity.getId();
        vooId = entity.getVooId();
        assentoId = entity.getAssentoId();
        nomeAssento = entity.getNomeAssento();
        reservado = entity.isReservado();
        valorAssento = entity.getValorAssento();
        valorBagagem = entity.getValorBagagem();
    }

    @Override
    public String toString() {
        return "VooAssentoDTO{" +
                "id=" + id +
                ", vooId='" + vooId + '\'' +
                ", assentoId='" + assentoId + '\'' +
                ", nomeAssento='" + nomeAssento + '\'' +
                ", valorAssento='" + valorAssento + '\'' +
                ", valorBagagem='" + valorBagagem + '\'' +
                ", reservado=" + reservado +
                '}';
    }


}