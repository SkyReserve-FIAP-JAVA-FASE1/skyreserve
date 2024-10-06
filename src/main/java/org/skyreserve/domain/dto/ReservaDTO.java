
package org.skyreserve.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;
import org.skyreserve.domain.entity.ReservaEntity;
import org.skyreserve.domain.enums.TipoVooEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReservaDTO {

    private Long id;
    private PassageiroDTO passageiroDTO;
    private VooDTO vooDTO;
    private AssentoDTO assentoDTO;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDaReserva;

    private boolean bagagem;
    private TipoVooEnum tipoVoo;
    private PagamentoDTO pagamento;
    private BigDecimal valorReserva;

    public ReservaDTO(ReservaEntity obj) {
        this.id = obj.getId();
        this.passageiroDTO = new PassageiroDTO(obj.getPassageiro());
        this.vooDTO = new VooDTO(obj.getVoo());
        this.assentoDTO = new AssentoDTO(obj.getAssento());
        this.dataDaReserva = obj.getDataDaReserva();
        this.bagagem = obj.isBagagem();
        this.tipoVoo = obj.getTipoVoo();
        this.pagamento = obj.getPagamento() != null && obj.getPagamento().getId() != null ? new PagamentoDTO(obj.getPagamento()) : null;
        this.valorReserva = obj.getValorReserva();
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id=" + id +
                ", passageiro=" + (passageiroDTO != null ? passageiroDTO.getId() : "null") +
                ", voo=" + (vooDTO != null ? vooDTO.getId() : "null") +
                ", assento=" + (assentoDTO != null ? assentoDTO.getId() : "null") +
                ", dataDaReserva=" + dataDaReserva +
                ", bagagem=" + bagagem +
                ", tipoVoo=" + tipoVoo +
                ", pagamento=" + (pagamento != null ? pagamento : "null") +
                ", valorReserva=" + valorReserva +
                '}';
    }

}