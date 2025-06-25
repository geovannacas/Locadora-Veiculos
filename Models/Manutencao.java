package Models;

import java.io.Serializable;
import java.time.LocalDate;

import Enum.TipoManutencaoEnum;

/**
 * Representa um registro de manutenção (preventiva, corretiva ou sinistro) de um veículo.
 */
public class Manutencao implements Serializable {

    private static int contadorId = 1;

    private int idManutencao;
    private int idVeiculo; // Chave estrangeira para o Veiculo
    private LocalDate dataAgendamento;
    private LocalDate dataRealizacao;
    private TipoManutencaoEnum tipoManutencao;
    private String descricaoServico;
    private double custo;

    /**
     * Construtor para agendar uma nova manutenção para um veículo.
     * @param idVeiculo ID do veículo que receberá a manutenção.
     * @param dataAgendamento Data em que a manutenção foi agendada.
     * @param tipoManutencao O tipo da manutenção (ex: PREVENTIVA, CORRETIVA).
     * @param descricaoServico Descrição inicial do serviço a ser realizado.
     */
    public Manutencao(int idVeiculo, LocalDate dataAgendamento, TipoManutencaoEnum tipoManutencao, String descricaoServico) {
        this.idManutencao = contadorId++;
        this.idVeiculo = idVeiculo;
        this.dataAgendamento = dataAgendamento;
        this.tipoManutencao = tipoManutencao;
        this.descricaoServico = descricaoServico;
        // Atributos como dataRealizacao e custo são preenchidos posteriormente.
        this.dataRealizacao = null;
        this.custo = 0.0;
    }

    // Getters e Setters
    public int getIdManutencao() {
        return idManutencao;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public TipoManutencaoEnum getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencaoEnum tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
    
    @Override
    public String toString() {
        return "Manutenção ID: " + idManutencao + 
               "\nVeículo ID: " + idVeiculo + 
               "\nTipo: " + tipoManutencao +
               "\nCusto: R$ " + String.format("%.2f", custo) +
               "\nData Agendada: " + dataAgendamento;
    }
}
