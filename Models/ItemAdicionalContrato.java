package Models;

import java.io.Serializable;

/**
 * Representa um item ou serviço opcional que pode ser adicionado a um contrato de aluguel.
 * Exemplos: cadeira de bebê, condutor adicional, seguro diferenciado.
 */
public class ItemAdicionalContrato implements Serializable {

    private static int contadorId = 1;

    private int idItemAdicionalContrato;
    private String descricaoItem;
    private double valorItem;
    private int quantidade;
    private int idContratoAluguel; // Chave estrangeira para o ContratoAluguel

    /**
     * Construtor para criar um novo item adicional associado a um contrato.
     * @param descricaoItem Descrição do item ou serviço.
     * @param valorItem Valor unitário do item.
     * @param quantidade Quantidade do item.
     * @param idContratoAluguel ID do contrato ao qual este item pertence.
     */
    public ItemAdicionalContrato(String descricaoItem, double valorItem, int quantidade, int idContratoAluguel) {
        this.idItemAdicionalContrato = contadorId++;
        this.descricaoItem = descricaoItem;
        this.valorItem = valorItem;
        this.quantidade = quantidade;
        this.idContratoAluguel = idContratoAluguel;
    }

    // Getters e Setters
    public int getIdItemAdicionalContrato() {
        return idItemAdicionalContrato;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdContratoAluguel() {
        return idContratoAluguel;
    }

    public void setIdContratoAluguel(int idContratoAluguel) {
        this.idContratoAluguel = idContratoAluguel;
    }

    @Override
    public String toString() {
        return "Item ID: " + idItemAdicionalContrato + 
               "\nDescrição: " + descricaoItem + 
               "\nValor: R$ " + String.format("%.2f", valorItem) +
               "\nQuantidade: " + quantidade;
    }
}