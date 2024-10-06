
package org.skyreserve.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.skyreserve.domain.dto.VooDTO;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voo")
public class VooEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A origem não pode estar vazia.")
    private String origem;

    @NotBlank(message = "O destino não pode estar vazio.")
    private String destino;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraPartida;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraChegada;

    @ManyToOne
    private AeronaveEntity aeronave;

    public VooEntity(VooDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.origem = dto.getOrigem();
            this.destino = dto.getDestino();
            this.dataHoraPartida = dto.getDataHoraPartida();
            this.dataHoraChegada = dto.getDataHoraChegada();
            this.aeronave = dto.getAeronave() != null && dto.getAeronave().getId() != null ? new AeronaveEntity(dto.getAeronave()) : null;
        }
    }

}