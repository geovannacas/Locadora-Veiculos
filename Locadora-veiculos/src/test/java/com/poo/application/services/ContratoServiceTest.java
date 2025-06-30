package com.poo.application.services;

import com.poo.domain.enums.StatusContratoEnum;
import com.poo.domain.models.Contrato;
import com.poo.infraestructure.repositories.ContratoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;

    @InjectMocks
    private ContratoService contratoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setIdCliente("cli1");
        contrato.setIdVeiculo("vei1");
        contrato.setDataHoraRetiradaPrevista(LocalDateTime.now());
        contrato.setDataHoraDevolucaoPrevista(LocalDateTime.now().plusDays(1));
        contrato.setStatusContrato(StatusContratoEnum.ABERTO);

        contratoService.criarContrato(contrato);
        verify(contratoRepository, times(1)).Create(contrato);
    }

    @Test
    void deveLancarExcecaoSeIdClienteNuloOuVazio() {
        Contrato contrato = new Contrato();
        contrato.setIdCliente("");
        contrato.setIdVeiculo("vei1");
        contrato.setDataHoraRetiradaPrevista(LocalDateTime.now());
        contrato.setDataHoraDevolucaoPrevista(LocalDateTime.now().plusDays(1));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.criarContrato(contrato));
        assertEquals("ID do cliente é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeIdVeiculoNuloOuVazio() {
        Contrato contrato = new Contrato();
        contrato.setIdCliente("cli1");
        contrato.setIdVeiculo("");
        contrato.setDataHoraRetiradaPrevista(LocalDateTime.now());
        contrato.setDataHoraDevolucaoPrevista(LocalDateTime.now().plusDays(1));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.criarContrato(contrato));
        assertEquals("ID do veículo é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeDataHoraRetiradaPrevistaNula() {
        Contrato contrato = new Contrato();
        contrato.setIdCliente("cli1");
        contrato.setIdVeiculo("vei1");
        contrato.setDataHoraRetiradaPrevista(null);
        contrato.setDataHoraDevolucaoPrevista(LocalDateTime.now().plusDays(1));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.criarContrato(contrato));
        assertEquals("Data/hora de retirada prevista é obrigatória", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeDataHoraDevolucaoPrevistaNula() {
        Contrato contrato = new Contrato();
        contrato.setIdCliente("cli1");
        contrato.setIdVeiculo("vei1");
        contrato.setDataHoraRetiradaPrevista(LocalDateTime.now());
        contrato.setDataHoraDevolucaoPrevista(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.criarContrato(contrato));
        assertEquals("Data/hora de devolução prevista é obrigatória", ex.getMessage());
    }

    @Test
    void deveDefinirStatusContratoPadraoSeNulo() {
        Contrato contrato = new Contrato();
        contrato.setIdCliente("cli1");
        contrato.setIdVeiculo("vei1");
        contrato.setDataHoraRetiradaPrevista(LocalDateTime.now());
        contrato.setDataHoraDevolucaoPrevista(LocalDateTime.now().plusDays(1));
        contrato.setStatusContrato(null);

        contratoService.criarContrato(contrato);
        assertEquals(StatusContratoEnum.ABERTO, contrato.getStatusContrato());
        verify(contratoRepository, times(1)).Create(contrato);
    }

    @Test
    void deveAtualizarContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("c1");
        when(contratoRepository.GetById("c1")).thenReturn(contrato);

        contratoService.atualizarContrato(contrato);
        verify(contratoRepository, times(1)).Update(contrato);
    }

    @Test
    void deveLancarExcecaoSeAtualizarContratoSemId() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.atualizarContrato(contrato));
        assertEquals("ID do contrato é obrigatório para atualização", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeContratoNaoEncontradoParaAtualizar() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("c1");
        when(contratoRepository.GetById("c1")).thenReturn(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.atualizarContrato(contrato));
        assertEquals("Contrato não encontrado", ex.getMessage());
    }

    @Test
    void deveRemoverContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("c1");
        when(contratoRepository.GetById("c1")).thenReturn(contrato);

        contratoService.removerContrato("c1");
        verify(contratoRepository, times(1)).Delete("c1");
    }

    @Test
    void deveLancarExcecaoSeRemoverContratoSemId() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.removerContrato(""));
        assertEquals("ID do contrato é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeContratoNaoEncontradoParaRemover() {
        when(contratoRepository.GetById("c1")).thenReturn(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.removerContrato("c1"));
        assertEquals("Contrato não encontrado", ex.getMessage());
    }

    @Test
    void deveBuscarContratoPorIdComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("c1");
        when(contratoRepository.GetById("c1")).thenReturn(contrato);
        Contrato result = contratoService.buscarContratoPorId("c1");
        assertEquals(contrato, result);
    }

    @Test
    void deveLancarExcecaoSeBuscarContratoPorIdSemId() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.buscarContratoPorId(""));
        assertEquals("ID do contrato é obrigatório", ex.getMessage());
    }

    @Test
    void deveListarTodosContratos() {
        Contrato c1 = new Contrato();
        Contrato c2 = new Contrato();
        when(contratoRepository.GetAll()).thenReturn(Arrays.asList(c1, c2));
        List<Contrato> contratos = contratoService.listarTodosContratos();
        assertEquals(2, contratos.size());
    }

    @Test
    void deveAtualizarStatusContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("c1");
        when(contratoRepository.GetById("c1")).thenReturn(contrato);
        doNothing().when(contratoRepository).Update(contrato);
        contratoService.atualizarStatusContrato("c1", StatusContratoEnum.FECHADO);
        assertEquals(StatusContratoEnum.FECHADO, contrato.getStatusContrato());
        verify(contratoRepository, times(1)).Update(contrato);
    }

    @Test
    void deveLancarExcecaoSeAtualizarStatusContratoNaoEncontrado() {
        when(contratoRepository.GetById("c1")).thenReturn(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.atualizarStatusContrato("c1", StatusContratoEnum.FECHADO));
        assertEquals("Contrato não encontrado", ex.getMessage());
    }

    @Test
    void deveFinalizarContratoComSucesso() {
        Contrato contrato = new Contrato();
        contrato.setIdContrato("c1");
        when(contratoRepository.GetById("c1")).thenReturn(contrato);
        LocalDateTime dataDevolucao = LocalDateTime.now();
        contratoService.finalizarContrato("c1", dataDevolucao);
        assertEquals(dataDevolucao, contrato.getDataHoraDevolucaoReal());
        assertEquals(StatusContratoEnum.FECHADO, contrato.getStatusContrato());
        verify(contratoRepository, times(1)).Update(contrato);
    }

    @Test
    void deveLancarExcecaoSeFinalizarContratoNaoEncontrado() {
        when(contratoRepository.GetById("c1")).thenReturn(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> contratoService.finalizarContrato("c1", LocalDateTime.now()));
        assertEquals("Contrato não encontrado", ex.getMessage());
    }

    @Test
    void deveListarContratosAbertos() {
        Contrato c1 = new Contrato();
        c1.setStatusContrato(StatusContratoEnum.ABERTO);
        Contrato c2 = new Contrato();
        c2.setStatusContrato(StatusContratoEnum.FECHADO);
        when(contratoRepository.GetAll()).thenReturn(Arrays.asList(c1, c2));
        List<Contrato> abertos = contratoService.listarContratosAbertos();
        assertEquals(1, abertos.size());
        assertEquals(StatusContratoEnum.ABERTO, abertos.get(0).getStatusContrato());
    }

    @Test
    void deveListarContratosPorCliente() {
        Contrato c1 = new Contrato();
        c1.setIdCliente("cli1");
        Contrato c2 = new Contrato();
        c2.setIdCliente("cli2");
        when(contratoRepository.GetAll()).thenReturn(Arrays.asList(c1, c2));
        List<Contrato> doCliente1 = contratoService.listarContratosPorCliente("cli1");
        assertEquals(1, doCliente1.size());
        assertEquals("cli1", doCliente1.get(0).getIdCliente());
    }
}
