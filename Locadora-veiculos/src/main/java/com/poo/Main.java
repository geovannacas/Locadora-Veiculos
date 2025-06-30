package com.poo;

import com.poo.application.PainelPrincipal;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Inicializa o contexto Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.poo");
        // Obtém o painel principal do Spring
        PainelPrincipal painelPrincipal = context.getBean(PainelPrincipal.class);

        // Cria e exibe o JFrame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Locadora de Veículos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(painelPrincipal);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}