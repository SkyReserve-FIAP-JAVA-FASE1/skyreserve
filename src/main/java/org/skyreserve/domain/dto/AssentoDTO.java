package org.skyreserve.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AssentoDTO{

    private Long id;
    private String descricao;
    private boolean reservado;
    private AeronaveDTO aeronave;


    @Override
    public String toString() {
        return "AssentoDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", reservado=" + reservado +
                ", aeronave=" + (aeronave != null ? aeronave.getId() : "null") +
                '}';
    }


}