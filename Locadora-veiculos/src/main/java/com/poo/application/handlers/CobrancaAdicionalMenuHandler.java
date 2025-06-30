package com.poo.application.handlers;

import com.poo.application.services.CobrancaAdicionalService;
import com.poo.domain.enums.StatusCobrancaEnum;
import com.poo.domain.enums.TipoCobrancaEnum;
import com.poo.domain.models.CobrancaAdicional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CobrancaAdicionalMenuHandler {
    private final CobrancaAdicionalService cobrancaService;

    @Autowired
    public CobrancaAdicionalMenuHandler(CobrancaAdicionalService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    public void exibirMenuCobrancasAdicionais() {
        while (true) {
            String[] opcoes = {
                "Cadastrar Cobrança Adicional",
                "Listar Todas",
                "Buscar por ID",
                "Editar Cobrança",
                "Remover Cobrança",
                "Listar por Contrato",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(
                null,
                "Menu de Cobranças Adicionais",
                "Cobranças",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0 : {
                    String contratoId = JOptionPane.showInputDialog("ID do Contrato:");
                    TipoCobrancaEnum tipo = TipoCobrancaEnum.valueOf(JOptionPane.showInputDialog("Tipo de cobrança:").toUpperCase());
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor cobrado:"));
                    String statusStr = JOptionPane.showInputDialog("Status (PENDENTE, PAGA):");

                    try {
                        StatusCobrancaEnum status = StatusCobrancaEnum.valueOf(statusStr.toUpperCase());

                        CobrancaAdicional cobranca = new CobrancaAdicional();
                        cobranca.setIdContrato(contratoId);
                        cobranca.setTipoCobranca(tipo);
                        cobranca.setValorCobrado(valor);
                        cobranca.setDataEmissaoCobranca(LocalDateTime.now());
                        cobranca.setStatusCobranca(status);

                        cobrancaService.criarCobrancaAdicional(cobranca);
                        JOptionPane.showMessageDialog(null, "Cobrança criada com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                }

                case 1 : {
                    List<CobrancaAdicional> cobrancas = cobrancaService.listarTodasCobrancas();
                    StringBuilder sb = new StringBuilder("Cobranças Adicionais:\n\n");
                    for (CobrancaAdicional c : cobrancas) {
                        sb.append(c.toString()).append("\n"); 
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }

                case 2 : {
                    String id = JOptionPane.showInputDialog("ID da Cobrança:");
                    try {
                        CobrancaAdicional cobranca = cobrancaService.buscarCobrancaPorId(id);
                        JOptionPane.showMessageDialog(null, cobranca != null ? cobranca.toString() : "Cobrança não encontrada.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }

                case 3 : {
                    String id = JOptionPane.showInputDialog("ID da Cobrança para editar:");
                    CobrancaAdicional cobranca = cobrancaService.buscarCobrancaPorId(id);
                    if (cobranca != null) {
                        String novoContratoId = JOptionPane.showInputDialog("Novo ID do Contrato:", cobranca.getIdContrato());
                        TipoCobrancaEnum novoTipo = TipoCobrancaEnum.valueOf(JOptionPane.showInputDialog("Novo tipo:", cobranca.getTipoCobranca()).toUpperCase());
                        double novoValor = Double.parseDouble(JOptionPane.showInputDialog("Novo valor:", cobranca.getValorCobrado()));
                        String novoStatusStr = JOptionPane.showInputDialog("Novo status (PENDENTE, PAGA):", cobranca.getStatusCobranca().name());

                        try {
                            cobranca.setIdContrato(novoContratoId);
                            cobranca.setTipoCobranca(novoTipo);
                            cobranca.setValorCobrado(novoValor);
                            cobranca.setStatusCobranca(StatusCobrancaEnum.valueOf(novoStatusStr.toUpperCase()));

                            cobrancaService.atualizarCobrancaAdicional(cobranca);
                            JOptionPane.showMessageDialog(null, "Cobrança atualizada!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cobrança não encontrada.");
                    }
                }

                case 4 : {
                    String id = JOptionPane.showInputDialog("ID da Cobrança para remover:");
                    try {
                        cobrancaService.removerCobrancaAdicional(id);
                        JOptionPane.showMessageDialog(null, "Cobrança removida com sucesso.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }

                case 5 : {
                    String idContrato = JOptionPane.showInputDialog("ID do contrato:");
                    List<CobrancaAdicional> lista = cobrancaService.listarCobrancasPorContrato(idContrato);

                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhuma cobrança encontrada para este contrato.");
                    } else {
                        StringBuilder sb = new StringBuilder("Cobranças do Contrato:\n\n");
                        for (CobrancaAdicional c : lista) {
                            sb.append(c.toString()).append("\n"); 
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                }

                case 6:
case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
