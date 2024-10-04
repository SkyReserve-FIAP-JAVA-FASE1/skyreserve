package org.skyreserve.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("passageiro")
public class PassageiroEntity implements Serializable {

    @Id
    @Column("id")
    private Long id;

    @Column("nome")
    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @Column("cpf")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos.")
    private String cpf;

    @Column("email")
    @Email
    private String email;

    @Column("numeroPassaporte")
    @NotBlank(message = "O número do passaporte não pode estar vazio.")
    private String numeroPassaporte;

    @Column("dataNascimento")
    private LocalDate dataNascimento;

    @Column("celular")
    private String celular;

}