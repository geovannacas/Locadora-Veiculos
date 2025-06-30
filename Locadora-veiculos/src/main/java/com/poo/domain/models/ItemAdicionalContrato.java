package com.poo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document
public class ItemAdicionalContrato {
    @Id
    private String idItemAdicionalContrato;

    private String descricaoItem;
    private double valorItem;
    private int quantidade;
    private String idContratoAluguel;

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Item ID: " + idItemAdicionalContrato +
                "\nDescrição: " + descricaoItem +
                "\nValor: R$ " + String.format("%.2f", valorItem) +
                "\nQuantidade: " + quantidade;
    }
}
