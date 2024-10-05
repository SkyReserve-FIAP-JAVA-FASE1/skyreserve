package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.PassageiroDTO;
import org.skyreserve.domain.entity.PassageiroEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PassageiroService {

    @Autowired
    private PassageiroRepository repository;


    public PassageiroEntity findById(Long id) {
        Optional<PassageiroEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<PassageiroEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PassageiroEntity save(PassageiroDTO obj) {
        return repository.save(newPassageiro(obj));
    }

    public PassageiroEntity update(Long id, @Valid PassageiroDTO objDTO) {
        objDTO.setId(id);
        PassageiroEntity oldObj = findById(id);
        oldObj = newPassageiro(objDTO);
        return repository.save(oldObj);
    }

    private PassageiroEntity newPassageiro(PassageiroDTO obj) {
        PassageiroEntity passageiroEntity = new PassageiroEntity();
        if (obj.getId() != null) {
            passageiroEntity.setId(obj.getId());
        }

        passageiroEntity.setId(obj.getId());
        passageiroEntity.setNome(obj.getNome());
        passageiroEntity.setCpf(obj.getCpf());
        passageiroEntity.setEmail(obj.getEmail());
        passageiroEntity.setNumeroPassaporte(obj.getNumeroPassaporte());
        passageiroEntity.setDataNascimento(obj.getDataNascimento());
        passageiroEntity.setCelular(obj.getCelular());

        return passageiroEntity;
    }
}
