
package org.skyreserve.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.enums.TipoVooEnum;
import javax.persistence.Id;

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
@Table(name = "reserva")
public class ReservaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PassageiroEntity passageiro;

    @ManyToOne
    private VooEntity voo;

    @ManyToOne
    private AssentoEntity assento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDaReserva = LocalDateTime.now();

    private boolean bagagem = false;
    private TipoVooEnum tipoVoo = TipoVooEnum.IDA_E_VOLTA;

    @ManyToOne
    private PagamentoEntity pagamento;

    private BigDecimal valorReserva = BigDecimal.ZERO;

    public ReservaEntity(ReservaDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.passageiro = dto.getPassageiroDTO() != null && dto.getPassageiroDTO().getId() != null ? new PassageiroEntity(dto.getPassageiroDTO()) : null;
            this.dataDaReserva = dto.getDataDaReserva();
            this.bagagem = dto.isBagagem();
            this.tipoVoo = dto.getTipoVoo();
            this.valorReserva =  dto.getValorReserva();
            this.voo = dto.getVooDTO() != null && dto.getVooDTO().getId() != null ? new VooEntity(dto.getVooDTO()): null;
            this.assento = dto.getAssentoDTO() != null && dto.getAssentoDTO().getId() != null ? new AssentoEntity(dto.getAssentoDTO()): null;
            this.pagamento = dto.getPagamento() != null && dto.getPagamento().getId() != null ? new PagamentoEntity(dto.getPagamento()): null;
        }
    }

}