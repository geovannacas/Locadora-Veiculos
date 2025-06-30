package com.poo.application.handlers;

import com.poo.application.services.ContratoService;
import com.poo.domain.enums.StatusContratoEnum;
import com.poo.domain.models.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class ContratoMenuHandler {
    private final ContratoService contratoService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Autowired
    public ContratoMenuHandler(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    public void exibirMenuContratos() {
        while (true) {
            String[] opcoes = {
                "Cadastrar Contrato",
                "Listar Todos os Contratos",
                "Buscar Contrato por ID",
                "Editar Contrato",
                "Excluir Contrato",
                "Atualizar Status",
                "Finalizar Contrato",
                "Listar Contratos Abertos",
                "Listar por Cliente",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(
                null,
                "Menu de Contratos",
                "Contratos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0 : {
                    String clienteId = JOptionPane.showInputDialog("ID do Cliente:");
                    String veiculoId = JOptionPane.showInputDialog("ID do Veículo:");
                    String funcionarioIdEntrada = JOptionPane.showInputDialog("ID do Funcionário de Entrada: ");
                    String dataInicioStr = JOptionPane.showInputDialog("Data de Início (dd/MM/yyyy HH:mm):");
                    String dataFimStr = JOptionPane.showInputDialog("Data de Fim (dd/MM/yyyy HH:mm):");
                    double valorTotal = Double.parseDouble(JOptionPane.showInputDialog("Valor total:"));

                    try {
                        LocalDateTime dataInicio = LocalDateTime.parse(dataInicioStr, dateTimeFormatter);
                        LocalDateTime dataFim = LocalDateTime.parse(dataFimStr, dateTimeFormatter);

                        Contrato novoContrato = new Contrato();
                        novoContrato.setIdCliente(clienteId);
                        novoContrato.setIdVeiculo(veiculoId);
                        novoContrato.setIdFuncionarioEntrada(funcionarioIdEntrada);
                        novoContrato.setDataHoraRetiradaPrevista(dataInicio);
                        novoContrato.setDataHoraDevolucaoPrevista(dataFim);
                        novoContrato.setValorTotalPrevisto(valorTotal);
                        novoContrato.setStatusContrato(StatusContratoEnum.ABERTO);

                        contratoService.criarContrato(novoContrato);
                        JOptionPane.showMessageDialog(null, "Contrato cadastrado com sucesso!");
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Data inválida. Use o formato dd/MM/yyyy HH:mm.");
                    }
                }

                case 1 : {
                    List<Contrato> contratos = contratoService.listarTodosContratos();
                    StringBuilder sb = new StringBuilder("Contratos:\n\n");
                    for (Contrato c : contratos) {
                        sb.append(c.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 2 : {
                    String id = JOptionPane.showInputDialog("ID do Contrato:");
                    Contrato contrato = contratoService.buscarContratoPorId(id);
                    JOptionPane.showMessageDialog(null, contrato != null ? contrato.toString() : "Contrato não encontrado.");
                }

                case 3 : {
                    String id = JOptionPane.showInputDialog("ID do Contrato para editar:");
                    Contrato contrato = contratoService.buscarContratoPorId(id);
                    if (contrato != null) {
                        String novoClienteId = JOptionPane.showInputDialog("Novo ID do Cliente:", contrato.getIdCliente());
                        String novoVeiculoId = JOptionPane.showInputDialog("Novo ID do Veículo:", contrato.getIdVeiculo());
                        String novaDataInicioStr = JOptionPane.showInputDialog("Nova Data de Início (dd/MM/yyyy HH:mm):", dateTimeFormatter.format(contrato.getDataHoraRetiradaPrevista()));
                        String novaDataFimStr = JOptionPane.showInputDialog("Nova Data de Fim (dd/MM/yyyy HH:mm):", dateTimeFormatter.format(contrato.getDataHoraDevolucaoPrevista()));
                        double novoValorTotal = Double.parseDouble(JOptionPane.showInputDialog("Novo Valor Total:", contrato.getValorTotalPrevisto()));
                        StatusContratoEnum novoStatus = StatusContratoEnum.valueOf(JOptionPane.showInputDialog("Novo status (ATIVO, ENCERRADO):", contrato.getStatusContrato().name()).toUpperCase());

                        try {
                            contrato.setIdCliente(novoClienteId);
                            contrato.setIdVeiculo(novoVeiculoId);
                            contrato.setDataHoraRetiradaPrevista(LocalDateTime.parse(novaDataInicioStr, dateTimeFormatter));
                            contrato.setDataHoraDevolucaoPrevista(LocalDateTime.parse(novaDataFimStr, dateTimeFormatter));
                            contrato.setValorTotalPrevisto(novoValorTotal);
                            contrato.setStatusContrato(novoStatus);

                            contratoService.atualizarContrato(contrato);
                            JOptionPane.showMessageDialog(null, "Contrato atualizado!");
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Data inválida.");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Status inválido.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Contrato não encontrado.");
                    }
                }

                case 4 : {
                    String id = JOptionPane.showInputDialog("ID do Contrato para excluir:");
                    contratoService.removerContrato(id);
                    JOptionPane.showMessageDialog(null, "Contrato excluído.");
                }

                case 5 : {
                    String id = JOptionPane.showInputDialog("ID do Contrato:");
                    String novoStatusStr = JOptionPane.showInputDialog("Novo status (ABERTO, EM_ANDAMENTO, FECHADO):");
                    try {
                        StatusContratoEnum novoStatus = StatusContratoEnum.valueOf(novoStatusStr.toUpperCase());
                        contratoService.atualizarStatusContrato(id, novoStatus);
                        JOptionPane.showMessageDialog(null, "Status atualizado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Status inválido.");
                    }
                }

                case 6 : {
                    String id = JOptionPane.showInputDialog("ID do Contrato a finalizar:");
                    String dataDevolucaoStr = JOptionPane.showInputDialog("Data e hora de devolução real (dd/MM/yyyy HH:mm):");
                    try {
                        LocalDateTime devolucaoReal = LocalDateTime.parse(dataDevolucaoStr, dateTimeFormatter);
                        contratoService.finalizarContrato(id, devolucaoReal);
                        JOptionPane.showMessageDialog(null, "Contrato finalizado com sucesso!");
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Data inválida.");
                    }
                }

                case 7 : {
                    List<Contrato> abertos = contratoService.listarContratosAbertos();
                    StringBuilder sb = new StringBuilder("Contratos Abertos:\n\n");
                    for (Contrato c : abertos) {
                        sb.append(c.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 8 : {
                    String idCliente = JOptionPane.showInputDialog("ID do Cliente:");
                    List<Contrato> contratos = contratoService.listarContratosPorCliente(idCliente);
                    StringBuilder sb = new StringBuilder("Contratos do Cliente:\n\n");
                    for (Contrato c : contratos) {
                        sb.append(c.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 9:
case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
