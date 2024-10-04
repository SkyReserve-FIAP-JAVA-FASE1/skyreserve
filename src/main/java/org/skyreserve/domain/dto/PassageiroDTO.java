package org.skyreserve.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public PassageiroDTO(PassageiroDTO passageiroDTO) {
        id = passageiroDTO.getId();
        nome = passageiroDTO.getNome();
        cpf = passageiroDTO.getCpf();
        email = passageiroDTO.getEmail();
        numeroPassaporte = passageiroDTO.getNumeroPassaporte();
        dataNascimento = passageiroDTO.getDataNascimento();
        celular = passageiroDTO.getCelular();
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