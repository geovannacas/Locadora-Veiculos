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
    private Double valorOriginalMulta;
    private StatusMultaEnum statusMulta;

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "MultaTransito{" +
                "idMulta='" + idMulta + '\'' +
                ", idVeiculo='" + idVeiculo + '\'' +
                ", idContratoAluguel='" + (idContratoAluguel != null ? idContratoAluguel : "N/A") + '\'' +
                ", codigoNotificacao='" + codigoNotificacao + '\'' +
                ", dataOcorrenciaInfracao=" + dataOcorrenciaInfracao +
                ", dataNotificacaoLocadora=" + dataNotificacaoLocadora +
                ", descricaoInfracao='" + descricaoInfracao + '\'' +
                ", orgaoAutuador='" + orgaoAutuador + '\'' +
                ", valorOriginalMulta=" + String.format("%.2f", valorOriginalMulta) +
                ", statusMulta=" + statusMulta +
                '}';
    }
}
