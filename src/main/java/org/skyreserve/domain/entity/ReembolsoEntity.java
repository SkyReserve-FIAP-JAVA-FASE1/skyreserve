package org.skyreserve.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.skyreserve.domain.dto.ReembolsoDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reembolso")
public class ReembolsoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idReserva;

    private BigDecimal valorRestituicao = BigDecimal.ZERO;
    private boolean reembolsoEfetuado = false;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataReembolso;

    public ReembolsoEntity(ReembolsoDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.idReserva = dto.getIdReserva();
            this.valorRestituicao = dto.getValorRestituicao();
            this.reembolsoEfetuado = dto.isReembolsoEfetuado();
            this.dataSolicitacao = dto.getDataSolicitacao() != null ? dto.getDataSolicitacao() : null;
            this.dataReembolso = dto.getDataReembolso();
        }
    }

}