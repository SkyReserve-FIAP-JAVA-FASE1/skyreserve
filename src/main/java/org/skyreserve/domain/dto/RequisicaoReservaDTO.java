package org.skyreserve.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RequisicaoReservaDTO {

    private String cpf;
    private Long idVoo;
    private Long assentoSelecionado;
    private String formaPagamento;
    private boolean bagagem;


}