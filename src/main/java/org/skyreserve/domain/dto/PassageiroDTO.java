package org.skyreserve.domain.dto;

import lombok.*;
import org.skyreserve.domain.entity.PassageiroEntity;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PassageiroDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String numeroPassaporte;
    private LocalDate dataNascimento;
    private String celular;

    public PassageiroDTO(PassageiroEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.numeroPassaporte = entity.getNumeroPassaporte();
        this.dataNascimento = entity.getDataNascimento();
        this.celular = entity.getCelular();
    }

    @Override
    public String toString() {
        return "PassageiroDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", numeroPassaporte='" + numeroPassaporte + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", celular='" + celular + '\'' +
                '}';
    }


}