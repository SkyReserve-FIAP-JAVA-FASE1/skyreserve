package org.skyreserve.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("assento")
public class AssentoEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("descricao")
    private String descricao;

    @Column("reservado")
    private boolean reservado = false;

}