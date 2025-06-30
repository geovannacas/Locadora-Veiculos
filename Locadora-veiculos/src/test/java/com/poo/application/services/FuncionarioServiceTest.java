package com.poo.application.services;

import com.poo.domain.enums.NivelAcessoEnum;
import com.poo.domain.models.Funcionario;
import com.poo.infraestructure.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setLogin("joao");
        funcionario.setSenha("1234");
        funcionario.setCargo("Gerente");
        funcionario.setNivelAcesso(NivelAcessoEnum.GESTOR);

        funcionarioService.criarFuncionario(funcionario);
        verify(funcionarioRepository, times(1)).Create(funcionario);
    }

    @Test
    void deveLancarExcecaoSeNomeNuloOuVazio() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("");
        funcionario.setLogin("joao");
        funcionario.setSenha("1234");
        funcionario.setCargo("Gerente");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.criarFuncionario(funcionario));
        assertEquals("Nome do funcionário é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeLoginNuloOuVazio() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setLogin("");
        funcionario.setSenha("1234");
        funcionario.setCargo("Gerente");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.criarFuncionario(funcionario));
        assertEquals("Login é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeSenhaNulaOuVazia() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setLogin("joao");
        funcionario.setSenha("");
        funcionario.setCargo("Gerente");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.criarFuncionario(funcionario));
        assertEquals("Senha é obrigatória", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeCargoNuloOuVazio() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setLogin("joao");
        funcionario.setSenha("1234");
        funcionario.setCargo("");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.criarFuncionario(funcionario));
        assertEquals("Cargo é obrigatório", ex.getMessage());
    }

    @Test
    void deveDefinirNivelAcessoPadraoSeNulo() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setLogin("joao");
        funcionario.setSenha("1234");
        funcionario.setCargo("Gerente");
        funcionario.setNivelAcesso(null);

        funcionarioService.criarFuncionario(funcionario);
        assertEquals(NivelAcessoEnum.FUNCIONARIO, funcionario.getNivelAcesso());
        verify(funcionarioRepository, times(1)).Create(funcionario);
    }

    @Test
    void deveAtualizarFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId("1");
        when(funcionarioRepository.GetById("1")).thenReturn(funcionario);

        funcionarioService.atualizarFuncionario(funcionario);
        verify(funcionarioRepository, times(1)).Update(funcionario);
    }

    @Test
    void deveLancarExcecaoSeAtualizarFuncionarioSemId() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId("");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.atualizarFuncionario(funcionario));
        assertEquals("ID do funcionário é obrigatório para atualização", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeFuncionarioNaoEncontradoParaAtualizar() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId("1");
        when(funcionarioRepository.GetById("1")).thenReturn(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.atualizarFuncionario(funcionario));
        assertEquals("Funcionário não encontrado", ex.getMessage());
    }

    @Test
    void deveRemoverFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId("1");
        when(funcionarioRepository.GetById("1")).thenReturn(funcionario);

        funcionarioService.removerFuncionario("1");
        verify(funcionarioRepository, times(1)).Delete("1");
    }

    @Test
    void deveLancarExcecaoSeRemoverFuncionarioSemId() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.removerFuncionario(""));
        assertEquals("ID do funcionário é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeFuncionarioNaoEncontradoParaRemover() {
        when(funcionarioRepository.GetById("1")).thenReturn(null);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.removerFuncionario("1"));
        assertEquals("Funcionário não encontrado", ex.getMessage());
    }

    @Test
    void deveBuscarFuncionarioPorIdComSucesso() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId("1");
        when(funcionarioRepository.GetById("1")).thenReturn(funcionario);
        Funcionario result = funcionarioService.buscarFuncionarioPorId("1");
        assertEquals(funcionario, result);
    }

    @Test
    void deveLancarExcecaoSeBuscarFuncionarioPorIdSemId() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.buscarFuncionarioPorId(""));
        assertEquals("ID do funcionário é obrigatório", ex.getMessage());
    }

    @Test
    void deveAutenticarFuncionarioComSucesso() {
        Funcionario funcionario = new Funcionario();
        funcionario.setLogin("joao");
        funcionario.setSenha("1234");
        when(funcionarioRepository.GetAll()).thenReturn(Collections.singletonList(funcionario));
        Funcionario result = funcionarioService.autenticarFuncionario("joao", "1234");
        assertEquals(funcionario, result);
    }

    @Test
    void deveRetornarNullSeAutenticacaoFalhar() {
        Funcionario funcionario = new Funcionario();
        funcionario.setLogin("joao");
        funcionario.setSenha("1234");
        when(funcionarioRepository.GetAll()).thenReturn(Collections.singletonList(funcionario));
        Funcionario result = funcionarioService.autenticarFuncionario("joao", "senhaerrada");
        assertNull(result);
    }

    @Test
    void deveLancarExcecaoSeLoginAutenticacaoVazio() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.autenticarFuncionario("", "1234"));
        assertEquals("Login é obrigatório", ex.getMessage());
    }

    @Test
    void deveLancarExcecaoSeSenhaAutenticacaoVazia() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> funcionarioService.autenticarFuncionario("joao", ""));
        assertEquals("Senha é obrigatória", ex.getMessage());
    }

    @Test
    void deveListarFuncionariosPorNivelAcesso() {
        Funcionario f1 = new Funcionario();
        f1.setNivelAcesso(NivelAcessoEnum.GESTOR);
        Funcionario f2 = new Funcionario();
        f2.setNivelAcesso(NivelAcessoEnum.FUNCIONARIO);
        when(funcionarioRepository.GetAll()).thenReturn(Arrays.asList(f1, f2));
        List<Funcionario> admins = funcionarioService.listarFuncionariosPorNivelAcesso(NivelAcessoEnum.GESTOR);
        assertEquals(1, admins.size());
        assertEquals(NivelAcessoEnum.GESTOR, admins.get(0).getNivelAcesso());
    }
}
