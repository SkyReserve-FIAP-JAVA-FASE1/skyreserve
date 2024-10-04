package org.skyreserve.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("reembolso")
public class ReembolsoEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("reserva")
    @OneToOne
    private ReservaEntity reserva;

    @Column("valorRestituicao")
    private BigDecimal valorRestituicao = BigDecimal.ZERO;

    @Column("reembolsoEfetuado")
    private boolean reembolsoEfetuado = false;

    @Column("dataSolicitacao")
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column("dataReembolso")
    private LocalDateTime dataReembolso;

}