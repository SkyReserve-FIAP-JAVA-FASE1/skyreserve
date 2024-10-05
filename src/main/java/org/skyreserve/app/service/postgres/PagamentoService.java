package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.PagamentoDTO;
import org.skyreserve.domain.entity.PagamentoEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;


    public PagamentoEntity findById(Long id) {
        Optional<PagamentoEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<PagamentoEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PagamentoEntity save(PagamentoDTO obj) {
        return repository.save(newPagamento(obj));
    }

    public PagamentoEntity update(Long id, @Valid PagamentoDTO objDTO) {
        objDTO.setId(id);
        PagamentoEntity oldObj = findById(id);
        oldObj = newPagamento(objDTO);
        return repository.save(oldObj);
    }

    private PagamentoEntity newPagamento(PagamentoDTO obj) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        if (obj.getId() != null) {
            pagamentoEntity.setId(obj.getId());
        }

        pagamentoEntity.setId(obj.getId());
        pagamentoEntity.setStatusPagamento(obj.getStatusPagamento());
        pagamentoEntity.setDataDaReserva(obj.getDataDaReserva());
        pagamentoEntity.setBagagem(obj.isBagagem());
        pagamentoEntity.setTipoVoo(obj.getTipoVoo());
        pagamentoEntity.setValorTotal(obj.getValorTotal());

        return pagamentoEntity;
    }
}
