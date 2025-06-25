package Models;

import java.io.Serializable;
import java.time.LocalDate;

import Enum.StatusCobrancaEnum;
import Enum.TipoCobrancaEnum;

/**
 * Representa uma cobrança financeira feita ao cliente que não faz parte do valor padrão do aluguel.
 * Exemplos: indenizações por sinistros ou reembolsos de multas.
 */
public class CobrancaAdicional implements Serializable {
    
    private static int contadorId = 1;

    private int idCobranca;
    private int idContratoAluguel;
    private Integer idManutencao; // Opcional, relacionado a sinistro
    private Integer idMultaTransito; // Opcional, relacionado a multa
    private TipoCobrancaEnum tipoCobranca;
    private String descricaoDetalhada;
    private double valorCobrado;
    private LocalDate dataEmissaoCobranca;
    private LocalDate dataVencimentoPagamento;
    private StatusCobrancaEnum statusCobranca;

    /**
     * Construtor para criar uma nova cobrança adicional.
     * @param idContratoAluguel ID do contrato relacionado à cobrança.
     * @param tipoCobranca Tipo da cobrança (ex: INDENIZACAO_SINISTRO).
     * @param descricaoDetalhada Descrição detalhada do motivo da cobrança.
     * @param valorCobrado Valor a ser cobrado do cliente.
     * @param dataVencimentoPagamento Data de vencimento para pagamento.
     */
    public CobrancaAdicional(int idContratoAluguel, TipoCobrancaEnum tipoCobranca, String descricaoDetalhada, double valorCobrado, LocalDate dataVencimentoPagamento) {
        this.idCobranca = contadorId++;
        this.idContratoAluguel = idContratoAluguel;
        this.tipoCobranca = tipoCobranca;
        this.descricaoDetalhada = descricaoDetalhada;
        this.valorCobrado = valorCobrado;
        this.dataEmissaoCobranca = LocalDate.now(); // A data de emissão é a data atual
        this.dataVencimentoPagamento = dataVencimentoPagamento;
        this.statusCobranca = StatusCobrancaEnum.PENDENTE;
    }
    
    // Getters e Setters
    public int getIdCobranca() { return idCobranca; }
    public int getIdContratoAluguel() { return idContratoAluguel; }
    public Integer getIdManutencao() { return idManutencao; }
    public void setIdManutencao(Integer idManutencao) { this.idManutencao = idManutencao; }
    public Integer getIdMultaTransito() { return idMultaTransito; }
    public void setIdMultaTransito(Integer idMultaTransito) { this.idMultaTransito = idMultaTransito; }
    public TipoCobrancaEnum getTipoCobranca() { return tipoCobranca; }
    public void setTipoCobranca(TipoCobrancaEnum tipoCobranca) { this.tipoCobranca = tipoCobranca; }
    public double getValorCobrado() { return valorCobrado; }
    public void setValorCobrado(double valorCobrado) { this.valorCobrado = valorCobrado; }
    public StatusCobrancaEnum getStatusCobranca() { return statusCobranca; }
    public void setStatusCobranca(StatusCobrancaEnum statusCobranca) { this.statusCobranca = statusCobranca; }
    // ... outros getters e setters ...
    
    @Override
    public String toString() {
        return "Cobrança ID: " + idCobranca + 
               "\nContrato ID: " + idContratoAluguel + 
               "\nTipo: " + tipoCobranca +
               "\nValor: R$ " + String.format("%.2f", valorCobrado) +
               "\nStatus: " + statusCobranca;
    }
}
