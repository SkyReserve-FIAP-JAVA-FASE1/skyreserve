package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.entity.AssentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.AssentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AssentoService {

    @Autowired
    private AssentoRepository repository;


    public AssentoEntity findById(Long id) {
        Optional<AssentoEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<AssentoEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public AssentoEntity save(AssentoDTO obj) {
        return repository.save(newAssento(obj));
    }

    public AssentoEntity update(Long id, @Valid AssentoDTO objDTO) {
        objDTO.setId(id);
        AssentoEntity oldObj = findById(id);
        oldObj = newAssento(objDTO);
        return repository.save(oldObj);
    }

    private AssentoEntity newAssento(AssentoDTO obj) {
        AssentoEntity AssentoEntity = new AssentoEntity();
        if (obj.getId() != null) {
            AssentoEntity.setId(obj.getId());
        }

        AssentoEntity.setId(obj.getId());
        AssentoEntity.setDescricao(obj.getDescricao());
        AssentoEntity.setReservado(obj.isReservado());

        return AssentoEntity;
    }
}
