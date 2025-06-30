package com.poo.domain.models;

import com.poo.domain.enums.StatusContratoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document
public class Contrato {
    @Id
    private String idContrato;

    private String idCliente;
    private String idVeiculo;
    private LocalDateTime dataHoraRetiradaPrevista;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraRetiradaReal;
    private LocalDateTime dataHoraDevolucaoReal;
    private double valorTotalPrevisto;
    private double valorTotalPrevistoReal;
    private StatusContratoEnum statusContrato;
    private int kmSaida;
    private int kmEntrada;
    private String idFuncionarioEntrada; // Relacionamento com Funcionario
    private String idFuncionarioSaida; 

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Contrato {" +
                "idContrato='" + idContrato + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", idVeiculo='" + idVeiculo + '\'' +
                ", dataHoraRetiradaPrevista=" + dataHoraRetiradaPrevista +
                ", dataHoraDevolucaoPrevista=" + dataHoraDevolucaoPrevista +
                ", dataHoraRetiradaReal=" + dataHoraRetiradaReal +
                ", dataHoraDevolucaoReal=" + dataHoraDevolucaoReal +
                ", valorTotalPrevisto=" + valorTotalPrevisto +
                ", valorTotalPrevistoReal=" + valorTotalPrevistoReal +
                ", statusContrato=" + statusContrato +
                ", kmSaida=" + kmSaida +
                ", kmEntrada=" + kmEntrada +
                ", idFuncionarioEntrada='" + idFuncionarioEntrada + '\'' +
                ", idFuncionarioSaida='" + idFuncionarioSaida + '\'' +
                '}';
    }
}
