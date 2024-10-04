package org.skyreserve.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.OneToMany;
import java.io.Serializable;
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
    private List<AssentoDTO> assentos;
    private int limiteAssentos;

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