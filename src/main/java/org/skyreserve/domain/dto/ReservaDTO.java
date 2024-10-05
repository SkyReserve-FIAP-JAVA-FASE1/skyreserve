
package org.skyreserve.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.skyreserve.domain.entity.ReservaEntity;
import org.skyreserve.domain.enums.TipoVooEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReservaDTO {

    private Long id;
    private PassageiroEntity passageiro;
    private Long voo;
    private Long assento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDaReserva;

    private boolean bagagem;
    private TipoVooEnum tipoVoo;
    private Long pagamento;
    private BigDecimal valorReserva;

    public ReservaDTO(ReservaEntity obj) {
        this.id = obj.getId();
        this.passageiro = obj.getPassageiro();
        this.voo = Objects.nonNull(obj.getVoo()) ? obj.getVoo().getId() : null;
        this.assento = Objects.nonNull(obj.getAssento()) ? obj.getAssento().getId() : null;  // Converter AssentoEntity para AssentoDTO
        this.dataDaReserva = obj.getDataDaReserva();
        this.bagagem = obj.isBagagem();
        this.tipoVoo = obj.getTipoVoo();
        this.pagamento = Objects.nonNull(obj.getPagamento()) ?  obj.getPagamento().getId() : null;  // Converter PagamentoEntity para PagamentoDTO
        this.valorReserva = obj.getValorReserva();
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id=" + id +
                ", passageiro=" + (passageiro != null ? passageiro.getId() : "null") +
                ", voo=" + (voo != null ? voo : "null") +
                ", assento=" + (assento != null ? assento : "null") +
                ", dataDaReserva=" + dataDaReserva +
                ", bagagem=" + bagagem +
                ", tipoVoo=" + tipoVoo +
                ", pagamento=" + (pagamento != null ? pagamento : "null") +
                ", valorReserva=" + valorReserva +
                '}';
    }

}