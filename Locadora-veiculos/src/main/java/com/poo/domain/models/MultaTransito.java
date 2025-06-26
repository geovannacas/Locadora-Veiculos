package com.poo.domain.models;

import com.poo.domain.enums.StatusMultaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document
public class MultaTransito {
    @Id
    private String idMulta;

    private String idVeiculo;
    private String idContratoAluguel; // Pode ser nulo se a multa ocorreu com veículo não alugado
    private String codigoNotificacao;
    private LocalDateTime dataOcorrenciaInfracao;
    private LocalDate dataNotificacaoLocadora;
    private String descricaoInfracao;
    private String orgaoAutuador;
    private double valorOriginalMulta;
    private StatusMultaEnum statusMulta;

    @Override
    public String toString() {
        return "Multa ID: " + idMulta +
                "\nVeículo ID: " + idVeiculo +
                "\nContrato ID: " + (idContratoAluguel != null ? idContratoAluguel : "N/A") +
                "\nValor: R$ " + String.format("%.2f", valorOriginalMulta) +
                "\nStatus: " + statusMulta;
    }
}
