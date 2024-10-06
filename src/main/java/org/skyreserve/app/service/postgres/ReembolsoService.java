
package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.ReembolsoDTO;
import org.skyreserve.domain.entity.ReembolsoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.ReembolsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReembolsoService {

    @Autowired
    private ReembolsoRepository repository;


    public ReembolsoEntity findById(Long id) {
        Optional<ReembolsoEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<ReembolsoEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ReembolsoEntity save(ReembolsoDTO obj) {
        ReembolsoEntity item = newReembolso(obj);
        return repository.save(item);
    }

    public ReembolsoEntity update(Long id, @Valid ReembolsoDTO objDTO) {
        objDTO.setId(id);
        ReembolsoEntity oldObj = findById(id);
        oldObj = newReembolso(objDTO);
        return repository.save(oldObj);
    }

    private ReembolsoEntity newReembolso(ReembolsoDTO obj) {
        ReembolsoEntity reembolsoEntity = new ReembolsoEntity();
        reembolsoEntity.setId(obj.getId());
        reembolsoEntity.setIdReserva(obj.getIdReserva());
        reembolsoEntity.setValorRestituicao(obj.getValorRestituicao());
        reembolsoEntity.setReembolsoEfetuado(obj.isReembolsoEfetuado());
        reembolsoEntity.setDataSolicitacao(obj.getDataSolicitacao());
        reembolsoEntity.setDataReembolso(obj.getDataReembolso());

        return reembolsoEntity;
    }
}
