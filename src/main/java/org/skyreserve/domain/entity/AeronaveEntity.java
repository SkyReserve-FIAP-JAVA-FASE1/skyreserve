package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.AeronaveDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aeronave")
public class AeronaveEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A matrícula não pode ser vazia.")
    private String matricula = UUID.randomUUID().toString();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AssentoEntity> assentos;

    private int limiteAssentos = 1;

    public AeronaveEntity(AeronaveDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.matricula = dto.getMatricula();
            this.limiteAssentos = dto.getLimiteAssentos();
            this.assentos = dto.getAssentos().stream()
                    .filter(assentoDTO -> assentoDTO.getId() != null)
                    .map(AssentoEntity::new)
                    .collect(Collectors.toList());
        }
    }

}