package Models;
import java.io.Serializable;
import java.time.LocalDateTime;

import Enum.StatusContratoEnum;

public class ContratoAluguel implements Serializable {

    private static int contadorId = 1;

    private int idContrato;
    private int idCliente;
    private int idVeiculo;
    private LocalDateTime dataHoraRetiradaPrevista;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraRetiradaReal;
    private LocalDateTime dataHoraDevolucaoReal;
    private double valorTotalPrevisto;
    private StatusContratoEnum statusContrato;
    
    public ContratoAluguel(int idCliente, int idVeiculo, LocalDateTime dataHoraRetiradaPrevista, LocalDateTime dataHoraDevolucaoPrevista, double valorTotalPrevisto) {
        this.idContrato = contadorId++;
        this.idCliente = idCliente;
        this.idVeiculo = idVeiculo;
        this.dataHoraRetiradaPrevista = dataHoraRetiradaPrevista;
        this.dataHoraDevolucaoPrevista = dataHoraDevolucaoPrevista;
        this.valorTotalPrevisto = valorTotalPrevisto;
        this.statusContrato = StatusContratoEnum.ABERTO;
    }
    
    // Getters e Setters
    public int getIdContrato() { return idContrato; }
    public int getIdCliente() { return idCliente; }
    public int getIdVeiculo() { return idVeiculo; }
    public LocalDateTime getDataHoraRetiradaPrevista() { return dataHoraRetiradaPrevista; }
    public void setDataHoraRetiradaPrevista(LocalDateTime dataHoraRetiradaPrevista) { this.dataHoraRetiradaPrevista = dataHoraRetiradaPrevista; }
    public LocalDateTime getDataHoraDevolucaoPrevista() { return dataHoraDevolucaoPrevista; }
    public void setDataHoraDevolucaoPrevista(LocalDateTime dataHoraDevolucaoPrevista) { this.dataHoraDevolucaoPrevista = dataHoraDevolucaoPrevista; }
    public LocalDateTime getDataHoraRetiradaReal() { return dataHoraRetiradaReal; }
    public void setDataHoraRetiradaReal(LocalDateTime dataHoraRetiradaReal) { this.dataHoraRetiradaReal = dataHoraRetiradaReal; }
    public LocalDateTime getDataHoraDevolucaoReal() { return dataHoraDevolucaoReal; }
    public void setDataHoraDevolucaoReal(LocalDateTime dataHoraDevolucaoReal) { this.dataHoraDevolucaoReal = dataHoraDevolucaoReal; }
    public double getValorTotalPrevisto() { return valorTotalPrevisto; }
    public void setValorTotalPrevisto(double valorTotalPrevisto) { this.valorTotalPrevisto = valorTotalPrevisto; }
    public StatusContratoEnum getStatusContrato() { return statusContrato; }
    public void setStatusContrato(StatusContratoEnum statusContrato) { this.statusContrato = statusContrato; }
    
    @Override
    public String toString() {
        return "Contrato ID: " + idContrato + "\nCliente ID: " + idCliente + "\nVeículo ID: " + idVeiculo + "\nStatus: " + statusContrato;
    }
}