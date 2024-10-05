package org.skyreserve.app.service.postgres;

import lombok.extern.slf4j.Slf4j;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
import org.skyreserve.domain.entity.VooEntity;
import org.skyreserve.infra.exceptions.ObjectNotFoundException;
import org.skyreserve.infra.repository.postgres.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SolicitarReservaService {

    @Autowired
    private ReservaRepository repository;


    public ReservaEntity findById(Long id) {
        Optional<ReservaEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<ReservaEntity> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ReservaEntity save(ReservaDTO obj) {
        return repository.save(newReserva(obj));
    }

    public ReservaEntity update(Long id, @Valid ReservaDTO objDTO) {
        objDTO.setId(id);
        ReservaEntity oldObj = findById(id);
        oldObj = newReserva(objDTO);
        return repository.save(oldObj);
    }

    private ReservaEntity newReserva(ReservaDTO obj) {
        ReservaEntity reservaEntity = new ReservaEntity();
        if (obj.getId() != null) {
            reservaEntity.setId(obj.getId());
        }

        reservaEntity.setPassageiro(obj.getPassageiro());
        reservaEntity.setDataDaReserva(obj.getDataDaReserva());
        reservaEntity.setBagagem(obj.isBagagem());
        reservaEntity.setTipoVoo(obj.getTipoVoo());
        reservaEntity.setValorReserva(obj.getValorReserva());

        // TODO Fazer a busca do Voo, Assento e Pagamento, preencher o objeto para salvar.
        reservaEntity.setVoo(null);
        reservaEntity.setAssento(null);
        reservaEntity.setPagamento(null);

        return reservaEntity;
    }
}
