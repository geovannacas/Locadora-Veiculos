package com.poo.application.handlers;

import com.poo.application.services.ClienteService;
import com.poo.domain.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class ClienteMenuHandler {
    private final ClienteService clienteService;

    @Autowired
    public ClienteMenuHandler(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void exibirMenuClientes() {
        while (true) {
            String[] opcoes = {
                "Cadastrar Cliente",
                "Listar Todos os Clientes",
                "Buscar Cliente por ID",
                "Editar Cliente",
                "Excluir Cliente",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(
                null,
                "Menu de Clientes",
                "Clientes",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0 : {
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String cpfCnpj = JOptionPane.showInputDialog("CPF ou CNPJ:");
                    String cnh = JOptionPane.showInputDialog("CNH:");
                    String endereco = JOptionPane.showInputDialog("Endereço:");
                    String telefone = JOptionPane.showInputDialog("Telefone:");
                    String email = JOptionPane.showInputDialog("E-mail:");

                    Cliente novoCliente = Cliente.builder()
                                        .nome(nome)
                                        .email(email)
                                        .telefone(telefone)
                                        .cpfCnpj(cpfCnpj)
                                        .cnh(cnh)
                                        .endereco(endereco)
                                        .build();
                    clienteService.criarCliente(novoCliente);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                }

                case 1 : {
                    List<Cliente> clientes = clienteService.listarTodosClientes();
                    StringBuilder sb = new StringBuilder("Clientes:\n\n");
                    for (Cliente c : clientes) {
                        sb.append(c.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 2 : {
                    String idStr = JOptionPane.showInputDialog("ID do Cliente:");
                    Cliente cliente = clienteService.buscarClientePorId(idStr);
                    if (cliente != null) {
                        JOptionPane.showMessageDialog(null, cliente.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                }

                case 3 : {
                    String idStr = JOptionPane.showInputDialog("ID do Cliente para editar:");
                    Cliente cliente = clienteService.buscarClientePorId(idStr);
                    if (cliente != null) {
                        String novoNome = JOptionPane.showInputDialog("Novo nome:", cliente.getNome());
                        String novoCpfCnpj = JOptionPane.showInputDialog("Novo CPF/CNPJ:", cliente.getCpfCnpj());
                        String novoTelefone = JOptionPane.showInputDialog("Novo telefone:", cliente.getTelefone());
                        String novoEmail = JOptionPane.showInputDialog("Novo e-mail:", cliente.getEmail());
                        String novaCnh = JOptionPane.showInputDialog("Nova CNH:", cliente.getCnh());
                        String novoEndereco = JOptionPane.showInputDialog("Novo endereço:", cliente.getEndereco());

                        cliente.setNome(novoNome);
                        cliente.setCpfCnpj(novoCpfCnpj);
                        cliente.setTelefone(novoTelefone);
                        cliente.setEmail(novoEmail);
                        cliente.setCnh(novaCnh);
                        cliente.setEndereco(novoEndereco);

                        clienteService.atualizarCliente(cliente);
                        JOptionPane.showMessageDialog(null, "Cliente atualizado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    }
                }

                case 4 : {
                    String idStr = JOptionPane.showInputDialog("ID do Cliente para excluir:");
                    clienteService.removerCliente(idStr);
                    JOptionPane.showMessageDialog(null, "Cliente excluído.");
                }

                case 5:
case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
