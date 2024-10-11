package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.AeronaveDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("aeronave")
public class AeronaveEntity implements Serializable {

    @Id
    private Long id;

    private String matricula = UUID.randomUUID().toString();
    private int limiteAssentos = 1;

    public AeronaveEntity(AeronaveDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.matricula = dto.getMatricula();
            this.limiteAssentos = dto.getLimiteAssentos();
        }
    }

}