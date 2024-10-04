
package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
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
    private PassageiroEntity passageiro;
    private VooDTO voo;
    private AssentoDTO assento;
    private LocalDateTime dataDaReserva;
    private boolean bagagem;
    private TipoVooEnum tipoVoo;
    private PagamentoDTO pagamento;
    private BigDecimal valorReserva;

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id=" + id +
                ", passageiro=" + (passageiro != null ? passageiro.getId() : "null") +
                ", voo=" + (voo != null ? voo.getId() : "null") +
                ", assento=" + (assento != null ? assento.getId() : "null") +
                ", dataDaReserva=" + dataDaReserva +
                ", bagagem=" + bagagem +
                ", tipoVoo=" + tipoVoo +
                ", pagamento=" + (pagamento != null ? pagamento.getId() : "null") +
                ", valorReserva=" + valorReserva +
                '}';
    }

}