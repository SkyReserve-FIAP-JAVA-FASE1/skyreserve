package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.domain.entity.AeronaveEntity;
import org.skyreserve.domain.entity.AssentoEntity;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        VooEntity vooEntity = new VooEntity();
        if (obj.getId() != null) {
            vooEntity.setId(obj.getId());
        }

        vooEntity.setId(obj.getId());
        vooEntity.setOrigem(obj.getOrigem());
        vooEntity.setDestino(obj.getDestino());
        vooEntity.setDataHoraChegada(obj.getDataHoraChegada());
        vooEntity.setDataHoraPartida(obj.getDataHoraPartida());

        if(Objects.nonNull(obj.getAeronave())){
            vooEntity.setAeronave(AeronaveEntity.builder()
                    .id(obj.getAeronave().getId())
                    .limiteAssentos(obj.getAeronave().getLimiteAssentos())
                    .matricula(obj.getAeronave().getMatricula())
                    .assentos(obj.getAeronave().getAssentos()
                            .stream()
                            .map(assento -> AssentoEntity.builder()
                                    .id(assento.getId())
                                    .descricao(assento.getDescricao())
                                    .reservado(assento.isReservado())
                                    .build())
                            .collect(Collectors.toList()))
                    .build());
        }

        return vooEntity;
    }
}
