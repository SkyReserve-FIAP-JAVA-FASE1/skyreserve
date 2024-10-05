package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VooService {

    @Autowired
    private VooRepository repository;


    public VooEntity findById(Long id) {
        Optional<VooEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<VooEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public VooEntity save(VooDTO obj) {
        return repository.save(newVoo(obj));
    }

    public VooEntity update(Long id, @Valid VooDTO objDTO) {
        objDTO.setId(id);
        VooEntity oldObj = findById(id);
        oldObj = newVoo(objDTO);
        return repository.save(oldObj);
    }

    private VooEntity newVoo(VooDTO obj) {
        VooEntity VooEntity = new VooEntity();
        if (obj.getId() != null) {
            VooEntity.setId(obj.getId());
        }

        VooEntity.setId(obj.getId());
        VooEntity.setOrigem(obj.getOrigem());
        VooEntity.setDestino(obj.getDestino());
        VooEntity.setDataHoraChegada(obj.getDataHoraChegada());
        VooEntity.setDataHoraPartida(obj.getDataHoraPartida());

        return VooEntity;
    }
}
