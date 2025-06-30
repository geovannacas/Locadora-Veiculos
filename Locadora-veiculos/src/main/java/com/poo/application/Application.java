package com.poo.application;

import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class Application {

    private final IRepository<Contrato> contratoRepository;

    @Autowired
    public Application(IRepository<Contrato> contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public static void Run() {
            System.out.println("Sprinsg Framework inicializado com sucesso!");
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Bem-vindo ao sistema de gerenciamento de contratos!");
            panel.add(label);
            JOptionPane.showMessageDialog(null, panel, "Sistema de Gerenciamento", JOptionPane.INFORMATION_MESSAGE);
    }
}