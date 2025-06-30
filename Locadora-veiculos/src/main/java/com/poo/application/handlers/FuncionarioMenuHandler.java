package com.poo.application.handlers;

import com.poo.application.services.FuncionarioService;
import com.poo.domain.enums.NivelAcessoEnum;
import com.poo.domain.models.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class FuncionarioMenuHandler {
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioMenuHandler(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public void exibirMenuFuncionarios() {
        while (true) {
            String[] opcoes = {
                "Cadastrar Funcionário",
                "Listar Todos",
                "Buscar por ID",
                "Editar Funcionário",
                "Remover Funcionário",
                "Autenticar Funcionário",
                "Listar por Nível de Acesso",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(
                null,
                "Menu de Funcionários",
                "Funcionários",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0 : {
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String login = JOptionPane.showInputDialog("Login:");
                    String senha = JOptionPane.showInputDialog("Senha:");
                    String cargo = JOptionPane.showInputDialog("Cargo:");
                    String nivel = JOptionPane.showInputDialog("Nível de acesso (ADMIN, FUNCIONARIO, GERENTE):");

                    try {
                        NivelAcessoEnum nivelEnum = NivelAcessoEnum.valueOf(nivel.toUpperCase());

                        Funcionario novo = Funcionario.builder()
                            .nome(nome)
                            .login(login)
                            .senha(senha)
                            .cargo(cargo)
                            .nivelAcesso(nivelEnum)
                            .build();

                        funcionarioService.criarFuncionario(novo);
                        JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Nível de acesso inválido.");
                    }
                }

                case 1 : {
                    List<Funcionario> funcionarios = funcionarioService.listarTodosFuncionarios();
                    StringBuilder sb = new StringBuilder("Funcionários:\n\n");
                    for (Funcionario f : funcionarios) {
                        sb.append(f.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 2 : {
                    String id = JOptionPane.showInputDialog("ID do Funcionário:");
                    Funcionario f = funcionarioService.buscarFuncionarioPorId(id);
                    JOptionPane.showMessageDialog(null, f != null ? f.toString() : "Funcionário não encontrado.");
                }

                case 3 : {
                    String id = JOptionPane.showInputDialog("ID do Funcionário para editar:");
                    Funcionario f = funcionarioService.buscarFuncionarioPorId(id);
                    if (f != null) {
                        String novoNome = JOptionPane.showInputDialog("Novo nome:", f.getNome());
                        String novoLogin = JOptionPane.showInputDialog("Novo login:", f.getLogin());
                        String novaSenha = JOptionPane.showInputDialog("Nova senha:", f.getSenha());
                        String novoCargo = JOptionPane.showInputDialog("Novo cargo:", f.getCargo());
                        String novoNivel = JOptionPane.showInputDialog("Novo nível de acesso (ADMIN, FUNCIONARIO, GERENTE):", f.getNivelAcesso().name());

                        try {
                            f.setNome(novoNome);
                            f.setLogin(novoLogin);
                            f.setSenha(novaSenha);
                            f.setCargo(novoCargo);
                            f.setNivelAcesso(NivelAcessoEnum.valueOf(novoNivel.toUpperCase()));
                            funcionarioService.atualizarFuncionario(f);
                            JOptionPane.showMessageDialog(null, "Funcionário atualizado!");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Nível de acesso inválido.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
                    }
                }

                case 4 : {
                    String id = JOptionPane.showInputDialog("ID do Funcionário para remover:");
                    try {
                        funcionarioService.removerFuncionario(id);
                        JOptionPane.showMessageDialog(null, "Funcionário removido.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }

                case 5 : {
                    String login = JOptionPane.showInputDialog("Login:");
                    String senha = JOptionPane.showInputDialog("Senha:");
                    Funcionario f = funcionarioService.autenticarFuncionario(login, senha);
                    JOptionPane.showMessageDialog(null, f != null ? "Autenticado: " + f.getNome() : "Credenciais inválidas.");
                }

                case 6 : {
                    String nivel = JOptionPane.showInputDialog("Nível de acesso para listar (ADMIN, FUNCIONARIO, GERENTE):");
                    try {
                        NivelAcessoEnum nivelEnum = NivelAcessoEnum.valueOf(nivel.toUpperCase());
                        List<Funcionario> lista = funcionarioService.listarFuncionariosPorNivelAcesso(nivelEnum);

                        StringBuilder sb = new StringBuilder("Funcionários com nível ").append(nivelEnum).append(":\n\n");
                        for (Funcionario f : lista) {
                            sb.append(f.toString()).append("\n"); 
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Nível de acesso inválido.");
                    }
                }

                case 7:
case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
