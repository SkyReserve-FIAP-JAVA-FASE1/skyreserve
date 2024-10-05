package org.skyreserve.app.service.TestLocal;

import org.skyreserve.app.service.postgres.*;
import org.skyreserve.domain.dto.AeronaveDTO;
import org.skyreserve.domain.dto.AssentoDTO;
import org.skyreserve.domain.dto.PassageiroDTO;
import org.skyreserve.domain.dto.VooDTO;
import org.skyreserve.infra.repository.postgres.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DBServiceLocal {

	@Autowired
	private PassageiroService passageiroService;

	@Autowired
	private AssentoService assentoService;

	@Autowired
	private AeronaveService aeronaveService;

	@Autowired
	private VooService vooService;

	@Autowired
	private SolicitarReservaService solicitarReservaService;

	@Autowired
	private PagamentoService pagamentoService;


	public void instanciaDB() {
		System.out.println("Iniciando a aplicação com valores iniciais.");

		passageiroService.save(PassageiroDTO.builder()
				.cpf("22233344488")
				.numeroPassaporte("1234")
				.nome("Teste")
			.build());

		assentoService.save(AssentoDTO.builder().descricao("A1").build());

		aeronaveService.save(AeronaveDTO.builder().matricula("123").build());

		vooService.save(VooDTO.builder()
				.origem("São Paulo")
				.destino("Rio de Janeiro")
				.dataHoraChegada(LocalDateTime.now())
				.build());


	}
}
