
package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("pagamento")
public class PagamentoEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("reservas")
    @OneToMany
    private List<ReservaEntity> reservas;

    @Column("valorTotal")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column("statusPagamento")
    private StatusPagamentoEnum statusPagamento;

    @Column("dataDaReserva")
    private LocalDateTime dataDaReserva = LocalDateTime.now();

    @Column("bagagem")
    private boolean bagagem = false;

    @Column("tipoVoo")
    private TipoVooEnum tipoVoo = TipoVooEnum.IDA_E_VOLTA;

    @Column("valorReserva")
    private BigDecimal valorReserva = BigDecimal.ZERO;

}