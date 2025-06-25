package Models;

import java.io.Serializable;

import Abstract.Pessoa;

/**
 * Representa um cliente da locadora. Herda da classe Pessoa.
 */
public class Cliente extends Pessoa {
    
    private static int contadorId = 1;
    
    private String cpfCnpj;
    private String cnh;
    private String endereco;

    /**
     * Construtor para criar um novo cliente.
     * @param nome Nome do cliente (herdado de Pessoa).
     * @param telefone Telefone do cliente (herdado de Pessoa).
     * @param email Email do cliente (herdado de Pessoa).
     * @param cpfCnpj CPF ou CNPJ do cliente.
     * @param cnh CNH do cliente.
     * @param endereco Endereço do cliente.
     */
    public Cliente(String nome, String telefone, String email, String cpfCnpj, String cnh, String endereco) {
        super(nome, telefone, email); // Chama o construtor da classe pai (Pessoa)
        this.id = contadorId++; // Usa o 'id' da classe Pessoa e o incrementa
        this.cpfCnpj = cpfCnpj;
        this.cnh = cnh;
        this.endereco = endereco;
    }

    // Getters e Setters específicos de Cliente
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente ID: " + id + "\nNome: " + nome + "\nCPF/CNPJ: " + cpfCnpj + "\nCNH: " + cnh;
    }
}