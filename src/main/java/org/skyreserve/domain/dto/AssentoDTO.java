package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.AssentoEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AssentoDTO{

    private Long id;
    private String descricao;
    private boolean reservado;
    private Long aeronaveId;

    public AssentoDTO(AssentoEntity assentoEntity) {
        id = assentoEntity.getId();
        descricao = assentoEntity.getDescricao();
        reservado = assentoEntity.isReservado();
        aeronaveId = assentoEntity.getAeronaveId();
    }

    @Override
    public String toString() {
        return "AssentoDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", reservado=" + reservado +
                ", aeronave=" + aeronaveId +
                '}';
    }


}