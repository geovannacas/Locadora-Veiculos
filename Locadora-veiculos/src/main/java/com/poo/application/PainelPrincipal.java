package com.poo.application;

import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class PainelPrincipal extends JPanel {
    private final IRepository<Contrato> contratoRepository;

    @Autowired
    public PainelPrincipal(IRepository<Contrato> contratoRepository) {
        this.contratoRepository = contratoRepository;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Painel Principal - Contratos");
        add(label, BorderLayout.NORTH);

        JButton btnListar = new JButton("Listar Contratos");
        btnListar.addActionListener(e -> listarContratos());
        add(btnListar, BorderLayout.CENTER);
    }

    private void listarContratos() {
        StringBuilder sb = new StringBuilder();
        for (Contrato contrato : contratoRepository.GetAll()) {
            sb.append(contrato.toString()).append("\n\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Nenhum contrato encontrado.", "Contratos", JOptionPane.INFORMATION_MESSAGE);
    }
} 