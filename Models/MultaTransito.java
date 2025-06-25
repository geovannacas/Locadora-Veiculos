package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import Enum.StatusMultaEnum;

/**
 * Representa uma notificação de multa de trânsito associada a um veículo da frota.
 */
public class MultaTransito implements Serializable {

    private static int contadorId = 1;

    private int idMulta;
    private int idVeiculo;
    private Integer idContratoAluguel; // Pode ser nulo se a multa ocorreu com veículo não alugado
    private String codigoNotificacao;
    private LocalDateTime dataOcorrenciaInfracao;
    private LocalDate dataNotificacaoLocadora;
    private String descricaoInfracao;
    private String orgaoAutuador;
    private double valorOriginalMulta;
    private StatusMultaEnum statusMulta;

    /**
     * Construtor para registrar uma nova multa de trânsito.
     * @param idVeiculo ID do veículo multado.
     * @param idContratoAluguel ID do contrato vigente na ocorrência (pode ser null).
     * @param codigoNotificacao Código da notificação da multa.
     * @param dataOcorrenciaInfracao Data e hora da infração.
     * @param dataNotificacaoLocadora Data em que a locadora foi notificada.
     * @param descricaoInfracao Descrição da infração cometida.
     * @param orgaoAutuador Órgão responsável pela autuação.
     * @param valorOriginalMulta Valor original da multa.
     */
    public MultaTransito(int idVeiculo, Integer idContratoAluguel, String codigoNotificacao, LocalDateTime dataOcorrenciaInfracao, LocalDate dataNotificacaoLocadora, String descricaoInfracao, String orgaoAutuador, double valorOriginalMulta) {
        this.idMulta = contadorId++;
        this.idVeiculo = idVeiculo;
        this.idContratoAluguel = idContratoAluguel;
        this.codigoNotificacao = codigoNotificacao;
        this.dataOcorrenciaInfracao = dataOcorrenciaInfracao;
        this.dataNotificacaoLocadora = dataNotificacaoLocadora;
        this.descricaoInfracao = descricaoInfracao;
        this.orgaoAutuador = orgaoAutuador;
        this.valorOriginalMulta = valorOriginalMulta;
        this.statusMulta = StatusMultaEnum.PENDENTE_IDENTIFICACAO_CONDUTOR;
    }

    // Getters e Setters
    public int getIdMulta() { return idMulta; }
    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }
    public Integer getIdContratoAluguel() { return idContratoAluguel; }
    public void setIdContratoAluguel(Integer idContratoAluguel) { this.idContratoAluguel = idContratoAluguel; }
    public String getCodigoNotificacao() { return codigoNotificacao; }
    public void setCodigoNotificacao(String codigoNotificacao) { this.codigoNotificacao = codigoNotificacao; }
    public double getValorOriginalMulta() { return valorOriginalMulta; }
    public void setValorOriginalMulta(double valorOriginalMulta) { this.valorOriginalMulta = valorOriginalMulta; }
    public StatusMultaEnum getStatusMulta() { return statusMulta; }
    public void setStatusMulta(StatusMultaEnum statusMulta) { this.statusMulta = statusMulta; }
    // ... outros getters e setters ...

    @Override
    public String toString() {
        return "Multa ID: " + idMulta + 
               "\nVeículo ID: " + idVeiculo + 
               "\nContrato ID: " + (idContratoAluguel != null ? idContratoAluguel : "N/A") +
               "\nValor: R$ " + String.format("%.2f", valorOriginalMulta) +
               "\nStatus: " + statusMulta;
    }
}
