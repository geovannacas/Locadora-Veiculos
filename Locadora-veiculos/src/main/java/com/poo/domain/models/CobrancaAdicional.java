package com.poo.domain.models;

import com.poo.domain.enums.StatusCobrancaEnum;
import com.poo.domain.enums.TipoCobrancaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class CobrancaAdicional {
    @Id
    private String id;

    private String idContrato;
    private String idManutencao;
    private String idMultaTransito;
    private TipoCobrancaEnum tipoCobranca;
    private String descricaoDetalhada;
    private Double valorCobrado;
    private LocalDateTime dataEmissaoCobranca;
    private LocalDateTime dataVencimentoPagamento;
    private StatusCobrancaEnum statusCobranca;

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "CobrancaAdicional{" +
                "id='" + id + '\'' +
                ", idContrato='" + idContrato + '\'' +
                ", idManutencao='" + idManutencao + '\'' +
                ", idMultaTransito='" + idMultaTransito + '\'' +
                ", tipoCobranca=" + (tipoCobranca != null ? tipoCobranca.name() : null) +
                ", descricaoDetalhada='" + descricaoDetalhada + '\'' +
                ", valorCobrado=" + valorCobrado +
                ", dataEmissaoCobranca=" + (dataEmissaoCobranca != null ? dataEmissaoCobranca.toString() : null) +
                ", dataVencimentoPagamento=" + (dataVencimentoPagamento != null ? dataVencimentoPagamento.toString() : null) +
                ", statusCobranca=" + (statusCobranca != null ? statusCobranca.name() : null) +
                '}';
    }
}
