
package org.skyreserve.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
import org.skyreserve.domain.enums.TipoVooEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamento")
public class PagamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "pagamento_reservas", joinColumns = @JoinColumn(name = "pagamento_id"))
    @Column(name = "reserva_id")
    private List<Long> reservas;

    private BigDecimal valorTotal;
    private StatusPagamentoEnum statusPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataDaReserva;

    private boolean bagagem = false;
    private TipoVooEnum tipoVoo;


    public PagamentoEntity(PagamentoDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.reservas = dto.getReservas() != null ? dto.getReservas() : null;
            this.valorTotal = dto.getValorTotal() != null ? dto.getValorTotal() : null;
            this.statusPagamento = dto.getStatusPagamento();
            this.dataDaReserva = dto.getDataDaReserva() != null ? dto.getDataDaReserva() : null;
            this.bagagem = dto.isBagagem();
            this.tipoVoo = dto.getTipoVoo() != null ? dto.getTipoVoo() : null;
        }
    }

}