package org.skyreserve.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
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
    @Column("id")
    private Long id;

    @Column("matricula")
    @NotBlank(message = "A matrícula não pode ser vazia.")
    private String matricula = UUID.randomUUID().toString();

    @Column("assentos")
    @OneToMany
    private List<AssentoEntity> assentos;

    @Column("limiteAssentos")
    private int limiteAssentos = 1;

}