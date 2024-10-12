package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.VooEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VooDTO {

    private Long id;

    private String origem;
    private String destino;
    private LocalDateTime dataHoraPartida;
    private LocalDateTime dataHoraChegada;
    private Long aeronaveId;

    public VooDTO(VooEntity entity) {
        this.id = entity.getId();
        this.origem = entity.getOrigem();
        this.destino = entity.getDestino();
        this.dataHoraPartida = entity.getDataHoraPartida();
        this.dataHoraChegada = entity.getDataHoraChegada();
        this.aeronaveId = entity.getAeronaveId();
    }


    @Override
    public String toString() {
        return "VooEntity{" +
                "id=" + id +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", dataHoraPartida=" + dataHoraPartida +
                ", dataHoraChegada=" + dataHoraChegada +
                ", aeronaveId=" + aeronaveId +
                '}';
    }


}