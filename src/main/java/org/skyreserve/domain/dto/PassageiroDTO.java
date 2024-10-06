package org.skyreserve.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    private String celular;

    public PassageiroDTO(PassageiroEntity passageiroEntity) {
        this.id = passageiroEntity.getId();
        this.nome = passageiroEntity.getNome();
        this.cpf = passageiroEntity.getCpf();
        this.email = passageiroEntity.getEmail();
        this.numeroPassaporte = passageiroEntity.getNumeroPassaporte();
        this.dataNascimento = passageiroEntity.getDataNascimento();
        this.celular = passageiroEntity.getCelular();
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