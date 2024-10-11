package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.AssentoDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("assento")
public class AssentoEntity implements Serializable {

    @Id
    private Long id;
    private String descricao;
    private boolean reservado = false;

    public AssentoEntity(AssentoDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.descricao = dto.getDescricao();
            this.reservado = dto.isReservado();
        }
    }

}