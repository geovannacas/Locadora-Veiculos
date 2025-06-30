package com.poo.application.facades;

import com.poo.application.factories.ServiceFactory;
import com.poo.application.handlers.ClienteMenuHandler;
import com.poo.application.handlers.VeiculoMenuHandler;
import com.poo.application.handlers.ContratoMenuHandler;
import com.poo.application.handlers.FuncionarioMenuHandler;
import com.poo.application.handlers.CobrancaAdicionalMenuHandler;
import com.poo.application.handlers.ItemAdicionalMenuHandler;
import com.poo.application.handlers.ManutencaoMenuHandler;
import com.poo.application.handlers.MultaTransitoMenuHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MenuFacade {
    
    private final ClienteMenuHandler clienteMenuHandler;
    private final VeiculoMenuHandler veiculoMenuHandler;
    private final ContratoMenuHandler contratoMenuHandler;
    private final FuncionarioMenuHandler funcionarioMenuHandler;
    private final CobrancaAdicionalMenuHandler cobrancaAdicionalMenuHandler;
    private final ItemAdicionalMenuHandler itemAdicionalMenuHandler;
    private final ManutencaoMenuHandler manutencaoMenuHandler;
    private final MultaTransitoMenuHandler multaTransitoMenuHandler;
    
    @Autowired
    public MenuFacade(ServiceFactory serviceFactory,
                      ClienteMenuHandler clienteMenuHandler,
                      VeiculoMenuHandler veiculoMenuHandler,
                      ContratoMenuHandler contratoMenuHandler,
                      FuncionarioMenuHandler funcionarioMenuHandler,
                      CobrancaAdicionalMenuHandler cobrancaAdicionalMenuHandler,
                      ItemAdicionalMenuHandler itemAdicionalMenuHandler,
                      ManutencaoMenuHandler manutencaoMenuHandler,
                      MultaTransitoMenuHandler multaTransitoMenuHandler) {
        this.clienteMenuHandler = clienteMenuHandler;
        this.veiculoMenuHandler = veiculoMenuHandler;
        this.contratoMenuHandler = contratoMenuHandler;
        this.funcionarioMenuHandler = funcionarioMenuHandler;
        this.cobrancaAdicionalMenuHandler = cobrancaAdicionalMenuHandler;
        this.itemAdicionalMenuHandler = itemAdicionalMenuHandler;
        this.manutencaoMenuHandler = manutencaoMenuHandler;
        this.multaTransitoMenuHandler = multaTransitoMenuHandler;
    }
    
    public void exibirMenuPrincipal() {
        while (true) {
            String[] opcoes = {
                "Gerenciar Clientes",
                "Gerenciar Veículos", 
                "Gerenciar Contratos",
                "Gerenciar Funcionários",
                "Gerenciar Cobranças Adicionais",
                "Gerenciar Itens Adicionais",
                "Gerenciar Manutenções",
                "Gerenciar Multas de Trânsito",
                "Sair"
            };
            
            int escolha = JOptionPane.showOptionDialog(
                null,
                "Sistema de Locação de Veículos\nSelecione uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );
            
            switch (escolha) {
                case 0: clienteMenuHandler.exibirMenuClientes();
                case 1: veiculoMenuHandler.exibirMenuVeiculos();
                case 2: contratoMenuHandler.exibirMenuContratos();
                case 3: funcionarioMenuHandler.exibirMenuFuncionarios();
                case 4 : cobrancaAdicionalMenuHandler.exibirMenuCobrancasAdicionais();
                case 5 : itemAdicionalMenuHandler.exibirMenuItensAdicionais();
                case 6 : manutencaoMenuHandler.exibirMenuManutencoes();
                case 7 : multaTransitoMenuHandler.exibirMenuMultasTransito();
                case 8:
                case JOptionPane.CLOSED_OPTION : {
                    JOptionPane.showMessageDialog(null, "Sistema encerrado!");
                    System.exit(0);
                }
            }
        }
    }
}