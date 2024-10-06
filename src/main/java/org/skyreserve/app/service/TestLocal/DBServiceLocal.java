package org.skyreserve.app.service.TestLocal;

import org.skyreserve.app.service.postgres.*;
import org.skyreserve.domain.dto.*;
import org.skyreserve.domain.entity.*;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private ReembolsoService reembolsoService;

    public void instanciaDB() {
        System.out.println("Iniciando a aplicação com valores iniciais.");

        // Criando passageiro default.
        if (passageiroService.findAll().isEmpty())
            passageiroService.save(PassageiroDTO.builder()
                    .cpf("22233344488")
                    .nome("Fulano de Tal")
                    .email("teste@teste.com")
                    .celular("11988887777")
                    .dataNascimento(LocalDate.now())
                    .numeroPassaporte("1234")
                    .build());

        // Criando aeronave default.
        if (aeronaveService.findAll().isEmpty()) {
            AssentoDTO assento1 = AssentoDTO.builder()
                    .descricao("A1")
                    .build();

            AssentoDTO assento2 = AssentoDTO.builder()
                    .descricao("A2")
                    .build();

            List<AssentoDTO> assentos = List.of(assento1, assento2);
            aeronaveService.save(AeronaveDTO.builder()
                    .limiteAssentos(30)
                    .assentos(assentos)
                    .matricula("123")
                    .build());
        }


        // Criando voo default.
        if (vooService.findAll().isEmpty()) {
            AeronaveEntity aeronaveEntity = aeronaveService.findAll().get(0);
            vooService.save(VooDTO.builder()
                    .origem("São Paulo")
                    .destino("Rio de Janeiro")
                    .dataHoraPartida(LocalDateTime.now())
                    .dataHoraChegada(LocalDateTime.now().plusHours(1))
                    .aeronave(new AeronaveDTO(aeronaveEntity))
                    .build());
        }

        // Criando uma reserva default de teste.
        if (solicitarReservaService.findAll().isEmpty()) {
            List<PagamentoEntity> listPagamentoEntity = pagamentoService.findAll();
            VooEntity vooEntity = vooService.findAll().get(0);
            PassageiroEntity passageiroEntity = passageiroService.findAll().get(0);
            AssentoEntity assentoEntity = assentoService.findAll().get(0);

            PagamentoEntity pagamentoEntity = listPagamentoEntity != null
                    && !listPagamentoEntity.isEmpty()
                    && listPagamentoEntity.get(0).getId() != null ? listPagamentoEntity.get(0) : null;

            ReservaEntity reservaEntity = ReservaEntity.builder()
                    .dataDaReserva(LocalDateTime.now())
                    .voo(vooEntity)
                    .passageiro(passageiroEntity)
                    .assento(assentoEntity)
                    .bagagem(true)
                    .tipoVoo(TipoVooEnum.IDA_E_VOLTA)
                    .valorReserva(new BigDecimal("599.99"))
                    .build();

            // Fazendo a reserva.
            ReservaEntity reservaEntitySaved = solicitarReservaService.save(new ReservaDTO(reservaEntity));

            // Fazendo o pagamento dessa viagem e atualizando.
            PagamentoEntity pagamentoSaved = pagamentoService.save(PagamentoDTO.builder()
                .valorTotal(new BigDecimal("899.95"))
                .statusPagamento(StatusPagamentoEnum.PAGO)
                .dataDaReserva(LocalDateTime.now())
                .bagagem(false)
                .tipoVoo(TipoVooEnum.IDA_E_VOLTA)
            .build());

            pagamentoSaved.setReservas(List.of(reservaEntitySaved.getId()));
            pagamentoService.update(pagamentoSaved.getId(), new PagamentoDTO(pagamentoSaved));

            ReservaEntity reserva = solicitarReservaService.findAll().get(0);
            PagamentoEntity pagamentoEntity2 = pagamentoService.findByIdWithReservas(pagamentoService.findAll().get(0).getId());
            reserva.setPagamento(pagamentoEntity2);
            solicitarReservaService.update(reserva.getId(), new ReservaDTO(reserva));
        }

        // Fazendo um reembolso de teste
        if (reembolsoService.findAll().isEmpty()) {
            ReservaEntity reservaEntity = solicitarReservaService.findAll().get(0);

            ReembolsoDTO item = ReembolsoDTO.builder()
                    .idReserva(reservaEntity.getId())
                    .valorRestituicao(new BigDecimal("98.99"))
                    .reembolsoEfetuado(true)
                    .dataSolicitacao(LocalDateTime.now())
                    .dataReembolso(LocalDateTime.now().plusHours(1))
                    .build();

            reembolsoService.save(item);
        }
    }
}
