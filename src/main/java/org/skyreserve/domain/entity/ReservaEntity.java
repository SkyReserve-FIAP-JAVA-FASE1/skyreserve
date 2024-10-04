
package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("reserva")
public class ReservaEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("passageiro")
    @ManyToOne
    private PassageiroEntity passageiro;

    @Column("voo")
    @ManyToOne
    private VooEntity voo;

    @Column("assento")
    @ManyToOne
    private AssentoEntity assento;

    @Column("dataDaReserva")
    private LocalDateTime dataDaReserva = LocalDateTime.now();

    @Column("bagagem")
    private boolean bagagem = false;

    @Column("tipoVoo")
    private TipoVooEnum tipoVoo = TipoVooEnum.IDA_E_VOLTA;

    @Column("pagamento")
    @ManyToOne
    private PagamentoEntity pagamento;

    @Column("valorReserva")
    private BigDecimal valorReserva = BigDecimal.ZERO;

}