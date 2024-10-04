
package org.skyreserve.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
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