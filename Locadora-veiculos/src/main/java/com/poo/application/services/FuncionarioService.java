package com.poo.application.services;

import com.poo.domain.models.Funcionario;
import com.poo.domain.enums.NivelAcessoEnum;
import com.poo.infraestructure.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    /** 
     * @param funcionario
     */
    public void criarFuncionario(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do funcionário é obrigatório");
        }
        if (funcionario.getLogin() == null || funcionario.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório");
        }
        if (funcionario.getSenha() == null || funcionario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        if (funcionario.getCargo() == null || funcionario.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo é obrigatório");
        }
        if (funcionario.getNivelAcesso() == null) {
            funcionario.setNivelAcesso(NivelAcessoEnum.FUNCIONARIO);
        }
        funcionarioRepository.Create(funcionario);
    }

    /** 
     * @param funcionario
     */
    public void atualizarFuncionario(Funcionario funcionario) {
        if (funcionario.getId() == null || funcionario.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do funcionário é obrigatório para atualização");
        }
        Funcionario funcionarioExistente = funcionarioRepository.GetById(funcionario.getId());
        if (funcionarioExistente == null) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        funcionarioRepository.Update(funcionario);
    }

    /** 
     * @param id
     */
    public void removerFuncionario(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do funcionário é obrigatório");
        }
        Funcionario funcionario = funcionarioRepository.GetById(id);
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não encontrado");
        }
        funcionarioRepository.Delete(id);
    }

    /** 
     * @param id
     * @return Funcionario
     */
    public Funcionario buscarFuncionarioPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do funcionário é obrigatório");
        }
        return funcionarioRepository.GetById(id);
    }

    /** 
     * @return List<Funcionario>
     */
    public List<Funcionario> listarTodosFuncionarios() {
        return (List<Funcionario>) funcionarioRepository.GetAll();
    }

    /** 
     * @param login
     * @param senha
     * @return Funcionario
     */
    public Funcionario autenticarFuncionario(String login, String senha) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login é obrigatório");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        
        return listarTodosFuncionarios().stream()
                .filter(f -> f.getLogin().equals(login) && f.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    /** 
     * @param nivelAcesso
     * @return List<Funcionario>
     */
    public List<Funcionario> listarFuncionariosPorNivelAcesso(NivelAcessoEnum nivelAcesso) {
        return listarTodosFuncionarios().stream()
                .filter(f -> f.getNivelAcesso() == nivelAcesso)
                .toList();
    }
}
