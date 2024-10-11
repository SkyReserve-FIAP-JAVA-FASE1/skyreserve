package org.skyreserve.domain.entity;

import lombok.*;
import org.skyreserve.domain.dto.PassageiroDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("passageiro")
public class PassageiroEntity implements Serializable {

    @Id
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String numeroPassaporte;
    private LocalDate dataNascimento;
    private String celular;

    public PassageiroEntity(PassageiroDTO dto) {
        if(dto != null){
            this.id = dto.getId();
            this.nome = dto.getNome();
            this.cpf = dto.getCpf();
            this.email = dto.getEmail();
            this.numeroPassaporte = dto.getNumeroPassaporte();
            this.dataNascimento = dto.getDataNascimento();
            this.celular = dto.getCelular();
        }
    }

}