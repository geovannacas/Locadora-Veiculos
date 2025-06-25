package Utils;
import java.io.*;
import java.util.ArrayList;

/**
 * Classe responsável por gravar e ler objetos em arquivos.
 * Usa o mecanismo de serialização do Java.
 */
public class Persistencia {
    
    /**
     * Grava uma lista de objetos em um arquivo binário.
     * @param nomeArquivo O nome do arquivo a ser criado/sobrescrito (ex: "clientes.dat").
     * @param lista A lista de objetos a ser gravada.
     */
    public static void gravar(String nomeArquivo, Object lista) {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(lista);
            
        } catch (IOException e) {
            // Em uma aplicação real, seria melhor usar um logger ou uma janela de erro.
            System.err.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }
    
    /**
     * Lê uma lista de objetos de um arquivo binário.
     * @param nomeArquivo O nome do arquivo a ser lido.
     * @return Um Object que precisa ser convertido (cast) para a lista apropriada (ex: ArrayList<Cliente>).
     * Retorna uma nova ArrayList vazia se o arquivo não for encontrado.
     */
    public static Object ler(String nomeArquivo) {
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            return ois.readObject();

        } catch (FileNotFoundException e) {
            // Isso é esperado na primeira vez que o programa roda.
            System.out.println("Arquivo " + nomeArquivo + " não encontrado. Um novo será criado ao salvar.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}