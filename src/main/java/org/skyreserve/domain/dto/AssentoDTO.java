package org.skyreserve.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.ManyToOne;
import java.io.Serializable;

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
    private AeronaveDTO aeronave;


    @Override
    public String toString() {
        return "AssentoDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", reservado=" + reservado +
                ", aeronave=" + (aeronave != null ? aeronave.getId() : "null") +
                '}';
    }


}