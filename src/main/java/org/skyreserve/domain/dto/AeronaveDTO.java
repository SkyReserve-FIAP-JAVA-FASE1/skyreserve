package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.AeronaveEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AeronaveDTO {

    private Long id;
    private String matricula;
    private int limiteAssentos;

    public AeronaveDTO(AeronaveEntity aeronaveEntity) {
        this.id = aeronaveEntity.getId();
        this.matricula = aeronaveEntity.getMatricula();
        this.limiteAssentos = aeronaveEntity.getLimiteAssentos();
    }


    @Override
    public String toString() {
        return "AeronaveDTO{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", limiteAssentos=" + limiteAssentos +
                '}';
    }

}