package org.skyreserve.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.skyreserve.app.service.kafka.producer.KafkaProducer;
import org.skyreserve.app.service.postgres.SolicitarReservaService;
import org.skyreserve.domain.dto.ReservaDTO;
import org.skyreserve.domain.entity.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reserva")
@Slf4j
public class ReservaController {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private SolicitarReservaService service;

    @Autowired
    private ObjectMapper obj;

    @PostMapping("/iniciar")
    public void start(@RequestBody ReservaDTO reservaDTO) throws IOException {
        log.info("Iniciando a aplicação.");
        producer.send(obj.writeValueAsString(reservaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ReservaDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findAll() {
        List<ReservaEntity> list = service.findAll();
        return ResponseEntity.ok().body(
                list.stream().map(ReservaDTO::new).collect(Collectors.toList()));
    }


    @PostMapping
    public ResponseEntity<ReservaDTO> save(@Valid @RequestBody ReservaDTO obj) {
        ReservaEntity newObj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservaDTO> update(@PathVariable Long id, @Valid @RequestBody ReservaDTO objDTO) {
        ReservaEntity newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ReservaDTO(newObj));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }


}