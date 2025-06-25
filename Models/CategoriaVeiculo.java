package Models;
import java.io.Serializable;

public class CategoriaVeiculo implements Serializable {

    private static int contadorId = 1;

    private int idCategoria;
    private String nome;
    private String descricao;
    private double valorDiariaBase;

    public CategoriaVeiculo(String nome, String descricao, double valorDiariaBase) {
        this.idCategoria = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.valorDiariaBase = valorDiariaBase;
    }

    // Getters e Setters
    public int getIdCategoria() { return idCategoria; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getValorDiariaBase() { return valorDiariaBase; }
    public void setValorDiariaBase(double valorDiariaBase) { this.valorDiariaBase = valorDiariaBase; }
    
    @Override
    public String toString() {
        return "Categoria ID: " + idCategoria + "\nNome: " + nome + "\nValor da Diária: R$ " + valorDiariaBase;
    }
}