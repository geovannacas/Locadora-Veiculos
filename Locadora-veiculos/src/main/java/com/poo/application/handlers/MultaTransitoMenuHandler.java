package com.poo.application.handlers;

import com.poo.application.services.MultaTransitoService;
import com.poo.domain.models.MultaTransito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MultaTransitoMenuHandler {
    private final MultaTransitoService multaService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Autowired
    public MultaTransitoMenuHandler(MultaTransitoService multaService) {
        this.multaService = multaService;
    }

    public void exibirMenuMultasTransito() {
        while (true) {
            String[] opcoes = {
                "Registrar Multa",
                "Buscar por ID",
                "Atualizar Multa",
                "Remover Multa",
                "Listar por Contrato",
                "Listar Todas",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(null, "Menu de Multas de Trânsito", "Multas",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0 : {
                    try {
                        String idContrato = JOptionPane.showInputDialog("ID do Contrato:");
                        String valorStr = JOptionPane.showInputDialog("Valor da multa:");
                        String dataStr = JOptionPane.showInputDialog("Data da inflação (dd/MM/yyyy HH:mm):");
                        LocalDateTime data = LocalDateTime.parse(dataStr, dateTimeFormatter);

                        MultaTransito m = new MultaTransito();
                        m.setIdContratoAluguel(idContrato);
                        m.setValorOriginalMulta(Double.parseDouble(valorStr));
                        m.setDataOcorrenciaInfracao(data);

                        multaService.registrarMulta(m);
                        JOptionPane.showMessageDialog(null, "Multa registrada com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                }
                case 1 : {
                    String id = JOptionPane.showInputDialog("ID da Multa:");
                    try {
                        MultaTransito m = multaService.buscarMultaPorId(id);
                        JOptionPane.showMessageDialog(null, m != null ? m.toString() : "Multa não encontrada.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
                case 2 : {
                    String id = JOptionPane.showInputDialog("ID da Multa para atualizar:");
                    MultaTransito m = multaService.buscarMultaPorId(id);
                    if (m != null) {
                        try {
                            String novoValorStr = JOptionPane.showInputDialog("Novo valor:", m.getValorOriginalMulta());
                            String novaDataStr = JOptionPane.showInputDialog("Nova data (dd/MM/yyyy HH:mm):", dateTimeFormatter.format(m.getDataOcorrenciaInfracao()));
                            LocalDateTime novaData = LocalDateTime.parse(novaDataStr, dateTimeFormatter);

                            m.setValorOriginalMulta(Double.parseDouble(novoValorStr));
                            m.setDataOcorrenciaInfracao(novaData);

                            multaService.atualizarMulta(m);
                            JOptionPane.showMessageDialog(null, "Atualizada com sucesso!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Multa não encontrada.");
                    }
                }
                case 3 : {
                    String id = JOptionPane.showInputDialog("ID da Multa para remover:");
                    try {
                        multaService.removerMulta(id);
                        JOptionPane.showMessageDialog(null, "Removida com sucesso.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
                case 4 : {
                    String idContrato = JOptionPane.showInputDialog("ID do Contrato:");
                    List<MultaTransito> lista = multaService.listarMultasPorContrato(idContrato);
                    StringBuilder sb = new StringBuilder("Multas do Contrato:\n\n");
                    for (MultaTransito m : lista) {
                        sb.append(m).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
                case 5 : {
                    List<MultaTransito> todas = multaService.listarTodasMultas();
                    StringBuilder sb = new StringBuilder("Todas as Multas:\n\n");
                    for (MultaTransito m : todas) {
                        sb.append(m).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
                case 6: 
                case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
