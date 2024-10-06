package org.skyreserve.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.skyreserve.domain.dto.PassageiroDTO;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passageiro")
public class PassageiroEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos.")
    private String cpf;

    @Email
    private String email;

    @NotBlank(message = "O número do passaporte não pode estar vazio.")
    private String numeroPassaporte;

    @JsonFormat(pattern = "dd/MM/yyyy")
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