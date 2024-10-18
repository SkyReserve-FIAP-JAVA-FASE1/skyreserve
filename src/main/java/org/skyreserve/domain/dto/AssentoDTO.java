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
    private String nome;
    private Long aeronaveId;

    public AssentoDTO(AssentoEntity assentoEntity) {
        id = assentoEntity.getId();
        nome = assentoEntity.getNome();
        descricao = assentoEntity.getDescricao();
        aeronaveId = assentoEntity.getAeronaveId();
    }

    @Override
    public String toString() {
        return "AssentoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", aeronave=" + aeronaveId +
                '}';
    }


}