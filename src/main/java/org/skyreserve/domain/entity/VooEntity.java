package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.VooDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
    private Long id;

    private String origem;
    private String destino;
    private LocalDateTime dataHoraPartida;
    private LocalDateTime dataHoraChegada;
    private Long aeronaveId;

    public VooEntity(VooDTO dto) {
        if (dto != null) {
            this.id = dto.getId();
            this.origem = dto.getOrigem();
            this.destino = dto.getDestino();
            this.dataHoraPartida = dto.getDataHoraPartida();
            this.dataHoraChegada = dto.getDataHoraChegada();
            this.aeronaveId = dto.getAeronaveId();
        }
    }
}

