package com.poo.domain.models;

import com.poo.domain.enums.StatusVeiculoEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Veiculo {
    @Id
    private String idVeiculo;

    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private String cor;
    private String chassi;
    private String idCategoria; // Relacionamento com CategoriaVeiculo
    private StatusVeiculoEnum status;
    private double kmAtual;

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Veiculo{" +
                "idVeiculo='" + idVeiculo + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", cor='" + cor + '\'' +
                ", chassi='" + chassi + '\'' +
                ", idCategoria='" + idCategoria + '\'' +
                ", status=" + status +
                ", kmAtual=" + kmAtual +
                '}';
    }
}
