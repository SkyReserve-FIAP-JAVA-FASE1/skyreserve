package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.skyreserve.domain.entity.AssentoEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AeronaveDTO {

    private Long id;
    private String matricula;
    private List<AssentoDTO> assentos;
    private int limiteAssentos;

    public AeronaveDTO(AeronaveEntity aeronaveEntity) {
        this.id = aeronaveEntity.getId();
        this.matricula = aeronaveEntity.getMatricula();
        this.limiteAssentos = aeronaveEntity.getLimiteAssentos();
        Integer assentosSize = aeronaveEntity.getAssentos() != null ? aeronaveEntity.getAssentos().size() : null;
        this.assentos = aeronaveEntity.getAssentos()
                .stream().map(AssentoDTO::new)
                .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "AeronaveDTO{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", assentos=" + assentos +
                ", limiteAssentos=" + limiteAssentos +
                '}';
    }

}