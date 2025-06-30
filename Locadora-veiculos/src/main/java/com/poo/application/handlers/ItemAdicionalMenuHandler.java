package com.poo.application.handlers;

import com.poo.application.services.ItemAdicionalContratoService;
import com.poo.domain.models.ItemAdicionalContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class ItemAdicionalMenuHandler {
    private final ItemAdicionalContratoService itemService;

    @Autowired
    public ItemAdicionalMenuHandler(ItemAdicionalContratoService itemService) {
        this.itemService = itemService;
    }

    public void exibirMenuItensAdicionais() {
        while (true) {
            String[] opcoes = {
                "Adicionar Item ao Contrato",
                "Buscar Item por ID",
                "Atualizar Item",
                "Remover Item",
                "Listar Itens por Contrato",
                "Voltar"
            };

            int escolha = JOptionPane.showOptionDialog(
                null,
                "Menu de Itens Adicionais",
                "Itens Adicionais",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0 : {
                    try {
                        String idContrato = JOptionPane.showInputDialog("ID do contrato:");
                        String descricao = JOptionPane.showInputDialog("Descrição do item:");
                        double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor do item:"));
                        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade:"));

                        ItemAdicionalContrato item = new ItemAdicionalContrato();
                        item.setIdContratoAluguel(idContrato);
                        item.setDescricaoItem(descricao);
                        item.setValorItem(valor);
                        item.setQuantidade(quantidade);

                        itemService.adicionarItemContrato(item);
                        JOptionPane.showMessageDialog(null, "Item adicionado com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                }

                case 1 : {
                    String id = JOptionPane.showInputDialog("ID do item:");
                    try {
                        ItemAdicionalContrato item = itemService.buscarItemPorId(id);
                        JOptionPane.showMessageDialog(null, item != null ? item.toString() : "Item não encontrado.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                }

                case 2 : {
                    String id = JOptionPane.showInputDialog("ID do item para atualizar:");
                    ItemAdicionalContrato item = itemService.buscarItemPorId(id);
                    if (item != null) {
                        try {
                            String novaDescricao = JOptionPane.showInputDialog("Nova descrição:", item.getDescricaoItem());
                            double novoValor = Double.parseDouble(JOptionPane.showInputDialog("Novo valor:", item.getValorItem()));
                            int novaQuantidade = Integer.parseInt(JOptionPane.showInputDialog("Nova quantidade:", item.getQuantidade()));

                            item.setDescricaoItem(novaDescricao);
                            item.setValorItem(novoValor);
                            item.setQuantidade(novaQuantidade);

                            itemService.atualizarItemContrato(item);
                            JOptionPane.showMessageDialog(null, "Item atualizado com sucesso!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Item não encontrado.");
                    }
                }

                case 3 : {
                    String id = JOptionPane.showInputDialog("ID do item para remover:");
                    try {
                        itemService.removerItemContrato(id);
                        JOptionPane.showMessageDialog(null, "Item removido com sucesso.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                    }
                }

                case 4 : {
                    String idContrato = JOptionPane.showInputDialog("ID do contrato:");
                    List<ItemAdicionalContrato> lista = itemService.listarItensPorContrato(idContrato);
                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum item encontrado para este contrato.");
                    } else {
                        StringBuilder sb = new StringBuilder("Itens adicionais do contrato:\n\n");
                        for (ItemAdicionalContrato item : lista) {
                            sb.append(item.toString()).append("\n"); 
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                }

                case 5:
case JOptionPane.CLOSED_OPTION : {
                    return;
                }
            }
        }
    }
}
