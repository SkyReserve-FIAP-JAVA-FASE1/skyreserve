package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.AeronaveDTO;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.skyreserve.domain.entity.AssentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.AeronaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AeronaveService {

    @Autowired
    private AeronaveRepository repository;

    @Autowired
    private AssentoService assentoService;


    public AeronaveEntity findById(Long id) {
        Optional<AeronaveEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<AeronaveEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public AeronaveEntity save(AeronaveDTO obj) {
        return repository.save(newAeronave(obj));
    }

    public AeronaveEntity update(Long id, @Valid AeronaveDTO objDTO) {
        objDTO.setId(id);
        AeronaveEntity oldObj = findById(id);
        oldObj = newAeronave(objDTO);
        return repository.save(oldObj);
    }

    private AeronaveEntity newAeronave(AeronaveDTO obj) {
        AeronaveEntity aeronaveEntity = new AeronaveEntity();
        if (obj.getId() != null) {
            aeronaveEntity.setId(obj.getId());
        }

        aeronaveEntity.setId(obj.getId());
        aeronaveEntity.setMatricula(obj.getMatricula());
        aeronaveEntity.setLimiteAssentos(obj.getLimiteAssentos());
        aeronaveEntity.setAssentos(obj.getAssentos()
                .stream()
                .map(assentoDTO -> AssentoEntity.builder()
                        .id(assentoDTO.getId())
                        .descricao(assentoDTO.getDescricao())
                        .reservado(assentoDTO.isReservado())
                        .build()).collect(Collectors.toList()));

        return aeronaveEntity;
    }
}
