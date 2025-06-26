package com.poo.domain.models;

import com.poo.domain.enums.TipoManutencaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Manutencao {
    @Id
    private String idManutencao;

    private String idVeiculo; // Chave estrangeira para o Veiculo
    private LocalDate dataAgendamento;
    private LocalDate dataRealizacao;
    private TipoManutencaoEnum tipoManutencao;
    private String descricaoServico;
    private double custo;

    @Override
    public String toString() {
        return "Manutenção ID: " + idManutencao +
                "\nVeículo ID: " + idVeiculo +
                "\nTipo: " + tipoManutencao +
                "\nCusto: R$ " + String.format("%.2f", custo) +
                "\nData Agendada: " + dataAgendamento;
    }
}
