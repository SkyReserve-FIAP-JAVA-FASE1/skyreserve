package org.skyreserve.app.service.TestLocal;

import org.skyreserve.app.service.postgres.*;
import org.skyreserve.domain.dto.*;
import org.skyreserve.domain.enums.StatusPagamentoEnum;
import org.skyreserve.domain.enums.TipoVooEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DBServiceLocal {

    @Value("${local}")
    boolean local;

    @Autowired
    private AeronaveService aeronaveService;

    @Autowired
    private AssentoService assentoService;

    @Autowired
    private PassageiroService passageiroService;

    @Autowired
    private VooService vooService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ReservaService reservaService;

    public void instanciaDB() {
        if (local) {
            System.out.println("Iniciando a aplicação com valores iniciais.");

            aeronaveService.save(AeronaveDTO.builder()
                    .limiteAssentos(50)
                    .matricula("123AXD1")
                    .build());

            assentoService.save(AssentoDTO.builder()
                    .aeronaveId(1L)
                    .descricao("A1")
                    .reservado(false)
                    .descricao("Fileira A - Assento 1")
                    .build());

            passageiroService.save(PassageiroDTO.builder()
                    .nome("Fulano de Tal da Silva")
                    .email("teste@teste.com")
                    .dataNascimento(LocalDate.now().plusYears(18))
                    .celular("(11)98765-4321")
                    .cpf("22299933344")
                    .numeroPassaporte("454889")
                    .build());

            passageiroService.save(PassageiroDTO.builder()
                    .nome("Fulano de Tal da Silva")
                    .email("teste@teste.com")
                    .dataNascimento(LocalDate.now().plusYears(18))
                    .celular("(11)98765-4321")
                    .cpf("22299933344")
                    .numeroPassaporte("454889")
                    .build());

            vooService.save(VooDTO.builder()
                    .dataHoraChegada(LocalDateTime.now().plusDays(5))
                    .dataHoraChegada(LocalDateTime.now().plusDays(5).plusHours(10))
                    .origem("São Paulo")
                    .destino("Rio de Janeiro")
                    .aeronaveId(1L)
                    .build());

            pagamentoService.save(PagamentoDTO.builder()
                    .dataPagamento(LocalDateTime.now())
                    .statusPagamento(StatusPagamentoEnum.PENDENTE_PAGAMENTO)
                    .valorTotal(new BigDecimal("598.56"))
                    .build());

            reservaService.save(ReservaDTO.builder()
                    .bagagem(false)
                    .tipoVoo(TipoVooEnum.IDA_E_VOLTA)
                    .valorReserva(new BigDecimal("598.56"))
                    .dataDaReserva(LocalDateTime.now())
                    .vooId(1L)
                    .assentoId(1L)
                    .passageiroId(1L)
                    .pagamentoId(1L)
                    .build());
        }
        
    }
}
