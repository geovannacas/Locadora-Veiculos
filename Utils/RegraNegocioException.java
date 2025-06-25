package Utils;
/**
 * Exceção personalizada para representar violações das regras de negócio do sistema.
 * Ex: tentar alugar um veículo indisponível, inserir dados inválidos.
 */
public class RegraNegocioException extends Exception {
    public RegraNegocioException(String message) {
        super(message);
    }
}