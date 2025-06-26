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
    private StatusContratoEnum statusContrato;

    @Override
    public String toString() {
        return "Contrato ID: " + idContrato + "\nCliente ID: " + idCliente + "\nVeículo ID: " + idVeiculo + "\nStatus: " + statusContrato;
    }
}
