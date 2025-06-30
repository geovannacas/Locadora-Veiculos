package com.poo.domain.models;

import com.poo.domain.enums.TipoManutencaoEnum;
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
@Getter
@Setter
@Document
public class Manutencao {
    @Id
    private String idManutencao;

    private String idVeiculo; // Chave estrangeira para o Veiculo
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataRealizacao;
    private TipoManutencaoEnum tipoManutencao;
    private String descricaoServico;
    private double custo;

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Manutencao{" +
                "idManutencao='" + idManutencao + '\'' +
                ", idVeiculo='" + idVeiculo + '\'' +
                ", dataAgendamento=" + dataAgendamento +
                ", dataRealizacao=" + dataRealizacao +
                ", tipoManutencao=" + tipoManutencao +
                ", descricaoServico='" + descricaoServico + '\'' +
                ", custo= R$ " + String.format("%.2f", custo) +
                '}';
    }
}
