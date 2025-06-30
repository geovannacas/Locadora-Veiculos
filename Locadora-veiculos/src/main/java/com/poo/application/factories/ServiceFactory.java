package com.poo.application.factories;

import com.poo.application.services.*;
import com.poo.application.services.ItemAdicionalContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final ContratoService contratoService;
    private final FuncionarioService funcionarioService;
    private final CobrancaAdicionalService cobrancaAdicionalService;
    private final ItemAdicionalContratoService itemAdicionalContratoService;
    private final ManutencaoService manutencaoService;
    private final MultaTransitoService multaTransitoService;


    @Autowired
    public ServiceFactory(ClienteService clienteService,
                            CobrancaAdicionalService cobrancaAdicionalService,
                            ItemAdicionalContratoService itemAdicionalContratoService,
                            ManutencaoService manutencaoService,
                            MultaTransitoService multaTransitoService, 
                            VeiculoService veiculoService,
                            ContratoService contratoService,
                            FuncionarioService funcionarioService) {
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
        this.contratoService = contratoService;
        this.funcionarioService = funcionarioService;
        this.cobrancaAdicionalService = cobrancaAdicionalService;
        this.itemAdicionalContratoService = itemAdicionalContratoService;
        this.manutencaoService = manutencaoService;
        this.multaTransitoService = multaTransitoService;
    }

    /** 
     * @return ClienteService
     */
    public ClienteService getClienteService() {
        return clienteService;
    }

    /** 
     * @return VeiculoService
     */
    public VeiculoService getVeiculoService() {
        return veiculoService;
    }

    /** 
     * @return ContratoService
     */
    public ContratoService getContratoService() {
        return contratoService;
    }

    /** 
     * @return FuncionarioService
     */
    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    /** 
     * @return CobrancaAdicionalService
     */
    public CobrancaAdicionalService getCobrancaAdicionalService() {
        return cobrancaAdicionalService;
    }

    /** 
     * @return ItemAdicionalContratoService
     */
    public ItemAdicionalContratoService getItemAdicionalContratoService() {
        return itemAdicionalContratoService;
    }

    /** 
     * @return ManutencaoService
     */
    public ManutencaoService getManutencaoService() {
        return manutencaoService;
    }

    /** 
     * @return MultaTransitoService
     */
    public MultaTransitoService getMultaTransitoService() {
        return multaTransitoService;
    }
}
