package com.poo.application.handlers;

import com.poo.application.services.ManutencaoService;
import com.poo.domain.enums.TipoManutencaoEnum;
import com.poo.domain.models.Manutencao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ManutencaoMenuHandler {
    private final ManutencaoService manutencaoService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Autowired
    public ManutencaoMenuHandler(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    public void exibirMenuManutencoes() {
        while (true) {
            String[] opcoes = {
                "Agendar Manutenção",
                "Buscar por ID",
                "Atualizar Manutenção",
                "Cancelar Manutenção",
                "Listar por Veículo",
                "Listar Todas",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(null, "Menu de Manutenções", "Manutenções",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0 : {
                    try {
                        String idVeiculo = JOptionPane.showInputDialog("ID do Veículo:");
                        TipoManutencaoEnum tipo = TipoManutencaoEnum.valueOf(JOptionPane.showInputDialog("Tipo de Manutenção:").toUpperCase());
                        String dataStr = JOptionPane.showInputDialog("Data de Agendamento (dd/MM/yyyy HH:mm):");
                        LocalDateTime data = LocalDateTime.parse(dataStr, dateTimeFormatter);

                        Manutencao m = new Manutencao();
                        m.setIdVeiculo(idVeiculo);
                        m.setTipoManutencao(tipo);
                        m.setDataAgendamento(data);

                        manutencaoService.agendarManutencao(m);
                        JOptionPane.showMessageDialog(null, "Manutenção agendada com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                }
                case 1 : {
                    String id = JOptionPane.showInputDialog("ID da Manutenção:");
                    try {
                        Manutencao m = manutencaoService.buscarManutencaoPorId(id);
                        JOptionPane.showMessageDialog(null, m != null ? m.toString() : "Manutenção não encontrada.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
                case 2 : {
                    String id = JOptionPane.showInputDialog("ID da Manutenção para atualizar:");
                    Manutencao m = manutencaoService.buscarManutencaoPorId(id);
                    if (m != null) {
                        try {
                            TipoManutencaoEnum novoTipo = TipoManutencaoEnum.valueOf(JOptionPane.showInputDialog("Novo tipo:", m.getTipoManutencao()));
                            String novaDataStr = JOptionPane.showInputDialog("Nova data (dd/MM/yyyy HH:mm):", dateTimeFormatter.format(m.getDataAgendamento()));
                            LocalDateTime novaData = LocalDateTime.parse(novaDataStr, dateTimeFormatter);

                            m.setTipoManutencao(novoTipo);
                            m.setDataAgendamento(novaData);

                            manutencaoService.atualizarManutencao(m);
                            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Manutenção não encontrada.");
                    }
                }
                case 3 : {
                    String id = JOptionPane.showInputDialog("ID da Manutenção para cancelar:");
                    try {
                        manutencaoService.cancelarManutencao(id);
                        JOptionPane.showMessageDialog(null, "Cancelada com sucesso.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
                case 4 : {
                    String idVeiculo = JOptionPane.showInputDialog("ID do Veículo:");
                    List<Manutencao> lista = manutencaoService.listarManutencoesPorVeiculo(idVeiculo);
                    StringBuilder sb = new StringBuilder("Manutenções do Veículo:\n\n");
                    for (Manutencao m : lista) {
                        sb.append(m).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
                case 5 : {
                    List<Manutencao> todas = manutencaoService.listarTodasManutencoes();
                    StringBuilder sb = new StringBuilder("Todas as Manutenções:\n\n");
                    for (Manutencao m : todas) {
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
