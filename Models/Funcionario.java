package Models;
import java.io.Serializable;

import Abstract.Pessoa;
import Enum.NivelAcessoEnum;

/**
 * Representa um funcionário da locadora. Herda da classe Pessoa.
 */
public class Funcionario extends Pessoa {

    private static int contadorId = 1;

    private String cargo;
    private String login;
    private String senha;
    private NivelAcessoEnum nivelAcesso;

    public Funcionario(String nome, String telefone, String email, String cargo, String login, String senha, NivelAcessoEnum nivelAcesso) {
        super(nome, telefone, email);
        this.id = contadorId++;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha; // Em um sistema real, a senha deve ser criptografada (hash)
        this.nivelAcesso = nivelAcesso;
    }
    
    // Getters e Setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcessoEnum getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcessoEnum nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public String toString() {
        return "Funcionário ID: " + id + "\nNome: " + nome + "\nCargo: " + cargo + "\nLogin: " + login;
    }
}