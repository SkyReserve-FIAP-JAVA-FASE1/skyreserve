
package org.skyreserve.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("voo")
public class VooEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("origem")
    @NotBlank(message = "A origem não pode estar vazia.")
    private String origem;

    @Column("destino")
    @NotBlank(message = "O destino não pode estar vazio.")
    private String destino;

    @Column("dataHoraPartida")
    private LocalDateTime dataHoraPartida;

    @Column("dataHoraChegada")
    private LocalDateTime dataHoraChegada;

    @Column("aeronave")
    private AeronaveEntity aeronave;

}