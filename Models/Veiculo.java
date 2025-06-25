package Models;
import java.io.Serializable;

import Enum.StatusVeiculoEnum;

public class Veiculo implements Serializable {
    
    private static int contadorId = 1;
    
    private int idVeiculo;
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private int idCategoria; // Relacionamento com CategoriaVeiculo
    private StatusVeiculoEnum status;
    private double kmAtual;

    public Veiculo(String placa, String modelo, String marca, int ano, int idCategoria, double kmAtual) {
        this.idVeiculo = contadorId++;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.idCategoria = idCategoria;
        this.kmAtual = kmAtual;
        this.status = StatusVeiculoEnum.DISPONIVEL;
    }

    // Getters e Setters
    public int getIdVeiculo() { return idVeiculo; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
    public StatusVeiculoEnum getStatus() { return status; }
    public void setStatus(StatusVeiculoEnum status) { this.status = status; }
    public double getKmAtual() { return kmAtual; }
    public void setKmAtual(double kmAtual) { this.kmAtual = kmAtual; }

    @Override
    public String toString() {
        return "Veículo ID: " + idVeiculo + "\nPlaca: " + placa + "\nModelo: " + modelo + "\nMarca: " + marca + "\nStatus: " + status;
    }
}