
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
    private AeronaveDTO aeronave;

    public VooDTO(VooEntity voo) {
        this.id = voo.getId();
        this.origem = voo.getOrigem();
        this.destino = voo.getDestino();
        this.dataHoraPartida = voo.getDataHoraPartida();
        this.dataHoraChegada = voo.getDataHoraChegada();
        this.aeronave = new AeronaveDTO(voo.getAeronave());
    }

    @Override
    public String toString() {
        return "VooDTO{" +
                "id=" + id +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", dataHoraPartida=" + dataHoraPartida +
                ", dataHoraChegada=" + dataHoraChegada +
                ", aeronave=" + (aeronave != null ? aeronave.getMatricula() : "null") +
                '}';
    }


}