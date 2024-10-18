package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.VooAssentoEntity;

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

    public VooAssentoDTO(VooAssentoEntity entity) {
        id = entity.getId();
        vooId = entity.getVooId();
        assentoId = entity.getAssentoId();
        nomeAssento = entity.getNomeAssento();
        reservado = entity.isReservado();
    }

    @Override
    public String toString() {
        return "VooAssentoDTO{" +
                "id=" + id +
                ", vooId='" + vooId + '\'' +
                ", assentoId='" + assentoId + '\'' +
                ", nomeAssento='" + nomeAssento + '\'' +
                ", reservado=" + reservado +
                '}';
    }


}