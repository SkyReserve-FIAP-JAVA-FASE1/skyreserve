package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.AssentoDTO;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assento")
public class AssentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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