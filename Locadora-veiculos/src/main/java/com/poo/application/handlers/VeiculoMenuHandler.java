package com.poo.application.handlers;

import com.poo.application.services.VeiculoService;
import com.poo.domain.enums.StatusVeiculoEnum;
import com.poo.domain.models.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class VeiculoMenuHandler {
    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoMenuHandler(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public void exibirMenuVeiculos() {
        while (true) {
            String[] opcoes = {
                "Cadastrar Veículo",
                "Listar Todos os Veículos",
                "Buscar Veículo por ID",
                "Editar Veículo",
                "Excluir Veículo",
                "Alterar Status do Veículo",
                "Listar Veículos Disponíveis",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(
                null,
                "Menu de Veículos",
                "Veículos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0 : {
                    String marca = JOptionPane.showInputDialog("Marca:");
                    String modelo = JOptionPane.showInputDialog("Modelo:");
                    int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano:"));
                    String placa = JOptionPane.showInputDialog("Placa:");
                    String cor = JOptionPane.showInputDialog("Cor:");
                    String chassi = JOptionPane.showInputDialog("Chassi:");
                    String idCategoria = JOptionPane.showInputDialog("ID da Categoria do Veículo:");
                    double quilometragem = Integer.parseInt(JOptionPane.showInputDialog("Quilometragem Atual:"));

                    Veiculo novo = new Veiculo();
                    novo.setMarca(marca);
                    novo.setModelo(modelo);
                    novo.setAno(ano);
                    novo.setPlaca(placa);
                    novo.setCor(cor);
                    novo.setChassi(chassi);
                    novo.setKmAtual(quilometragem);
                    novo.setIdCategoria(idCategoria);
                    novo.setStatus(StatusVeiculoEnum.DISPONIVEL); // novo veículo começa como disponível

                    veiculoService.criarVeiculo(novo);
                    JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
                }

                case 1 : {
                    List<Veiculo> veiculos = veiculoService.listarTodosVeiculos();
                    StringBuilder sb = new StringBuilder("Veículos:\n\n");
                    for (Veiculo v : veiculos) {
                        sb.append(v.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 2 : {
                    String id = JOptionPane.showInputDialog("ID do Veículo:");
                    Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
                    JOptionPane.showMessageDialog(null, veiculo != null ? veiculo.toString() : "Veículo não encontrado.");
                }

                case 3 : {
                    String id = JOptionPane.showInputDialog("ID do Veículo para editar:");
                    Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
                    if (veiculo != null) {
                        String novaMarca = JOptionPane.showInputDialog("Nova marca:", veiculo.getMarca());
                        String novoModelo = JOptionPane.showInputDialog("Novo modelo:", veiculo.getModelo());
                        int novoAno = Integer.parseInt(JOptionPane.showInputDialog("Novo ano:", veiculo.getAno()));
                        String novaPlaca = JOptionPane.showInputDialog("Nova placa:", veiculo.getPlaca());
                        String novaCor = JOptionPane.showInputDialog("Nova cor:", veiculo.getCor());
                        int novaKm = Integer.parseInt(JOptionPane.showInputDialog("Nova quilometragem:", veiculo.getKmAtual()));
                        String novoChassi = JOptionPane.showInputDialog("Novo chassi:", veiculo.getChassi());

                        veiculo.setMarca(novaMarca);
                        veiculo.setModelo(novoModelo);
                        veiculo.setAno(novoAno);
                        veiculo.setPlaca(novaPlaca);
                        veiculo.setCor(novaCor);
                        veiculo.setKmAtual(novaKm);
                        veiculo.setChassi(novoChassi);

                        veiculoService.atualizarVeiculo(veiculo);
                        JOptionPane.showMessageDialog(null, "Veículo atualizado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Veículo não encontrado.");
                    }
                }

                case 4 : {
                    String id = JOptionPane.showInputDialog("ID do Veículo para excluir:");
                    veiculoService.removerVeiculo(id);
                    JOptionPane.showMessageDialog(null, "Veículo excluído.");
                }

                case 5 : {
                    String id = JOptionPane.showInputDialog("ID do Veículo para alterar status:");
                    String status = JOptionPane.showInputDialog("Escolha um número para o status 1) DISPONÍVEL 2) ALUGADO 3) MANUTENÇÃO: ");
                    if(status.equals("1")) {
                        veiculoService.atualizarStatusVeiculo(id, StatusVeiculoEnum.DISPONIVEL);
                    } else if(status.equals("2")) {
                        veiculoService.atualizarStatusVeiculo(id, StatusVeiculoEnum.ALUGADO);
                    } else if(status.equals("3")) {
                        veiculoService.atualizarStatusVeiculo(id, StatusVeiculoEnum.EM_MANUTENCAO);
                    } else {
                        JOptionPane.showMessageDialog(null, "Status inválido.");
                        continue;
                    }
                    JOptionPane.showMessageDialog(null, "Status atualizado.");
                    return;
                }

                case 6 : {
                    List<Veiculo> veiculosDisponiveis = veiculoService.listarTodosVeiculos().stream()
                        .filter(v -> v.getStatus() == StatusVeiculoEnum.DISPONIVEL)
                        .toList();
                    StringBuilder sb = new StringBuilder("Veículos Disponíveis:\n\n");
                    for (Veiculo v : veiculosDisponiveis) {
                        sb.append(v.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 7:
case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
