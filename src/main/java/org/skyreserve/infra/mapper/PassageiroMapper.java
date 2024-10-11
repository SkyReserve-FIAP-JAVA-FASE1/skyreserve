package org.skyreserve.infra.mapper;

import org.skyreserve.domain.dto.PassageiroDTO;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.springframework.stereotype.Component;

@Component
public class PassageiroMapper {
    public PassageiroDTO toDTO(PassageiroEntity entity) {
        if (entity == null) {
            return null;
        }
        return PassageiroDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .numeroPassaporte(entity.getNumeroPassaporte())
                .dataNascimento(entity.getDataNascimento())
                .celular(entity.getCelular())
                .build();
    }

    public PassageiroEntity toEntity(PassageiroDTO dto) {
        if (dto == null) {
            return null;
        }
        return PassageiroEntity.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .numeroPassaporte(dto.getNumeroPassaporte())
                .dataNascimento(dto.getDataNascimento())
                .celular(dto.getCelular())
                .build();
    }
}