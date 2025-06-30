package com.poo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class CategoriaVeiculo {
    @Id
    private String id;

    private String nome;
    private String descricao;
    private double valorDiariaBase;

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "CategoriaVeiculo{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valorDiariaBase= R$ " + valorDiariaBase +
                '}';
    }
}
