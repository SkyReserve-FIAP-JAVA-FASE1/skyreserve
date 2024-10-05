
package org.skyreserve.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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
public class ReservaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PassageiroEntity passageiro;

    private VooEntity voo;
    private AssentoEntity assento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDaReserva = LocalDateTime.now();

    private boolean bagagem = false;
    private TipoVooEnum tipoVoo = TipoVooEnum.IDA_E_VOLTA;

    @ManyToOne
    private PagamentoEntity pagamento;

    private BigDecimal valorReserva = BigDecimal.ZERO;

}