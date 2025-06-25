package Abstract;
import java.io.Serializable;

/**
 * Classe abstrata que serve como modelo base para Cliente e Funcionario.
 * Não pode ser instanciada diretamente.
 * Implementa Serializable para permitir que seus objetos filhos sejam gravados em arquivo.
 */
public abstract class Pessoa implements Serializable {

    // Atributos protegidos são acessíveis pela própria classe e por suas subclasses.
    protected int id;
    protected String nome;
    protected String telefone;
    protected String email;

    // Construtor da classe base.
    public Pessoa(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}