package com.poo.application.services;

import com.poo.domain.models.Manutencao;
import com.poo.infraestructure.repositories.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;

    @Autowired
    public ManutencaoService(ManutencaoRepository manutencaoRepository) {
        this.manutencaoRepository = manutencaoRepository;
    }

    /** 
     * @param manutencao
     */
    public void agendarManutencao(Manutencao manutencao) {
        if (manutencao.getIdVeiculo() == null || manutencao.getIdVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do veículo é obrigatório");
        }
        if (manutencao.getDataAgendamento() == null) {
            throw new IllegalArgumentException("Data de agendamento é obrigatória");
        }
        if (manutencao.getTipoManutencao() == null) {
            throw new IllegalArgumentException("Tipo de manutenção é obrigatório");
        }
        manutencaoRepository.Create(manutencao);
    }

    /** 
     * @param manutencao
     */
    public void atualizarManutencao(Manutencao manutencao) {
        if (manutencao.getIdManutencao() == null || manutencao.getIdManutencao().trim().isEmpty()) {
            throw new IllegalArgumentException("ID da manutenção é obrigatório para atualização");
        }
        Manutencao existente = manutencaoRepository.GetById(manutencao.getIdManutencao());
        if (existente == null) {
            throw new IllegalArgumentException("Manutenção não encontrada");
        }
        manutencaoRepository.Update(manutencao);
    }

    /** 
     * @param id
     */
    public void cancelarManutencao(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da manutenção é obrigatório");
        }
        if (manutencaoRepository.GetById(id) == null) {
            throw new IllegalArgumentException("Manutenção não encontrada");
        }
        manutencaoRepository.Delete(id);
    }

    /** 
     * @param id
     * @return Manutencao
     */
    public Manutencao buscarManutencaoPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da manutenção é obrigatório");
        }
        return manutencaoRepository.GetById(id);
    }

    /** 
     * @return List<Manutencao>
     */
    public List<Manutencao> listarTodasManutencoes() {
        return (List<Manutencao>) manutencaoRepository.GetAll();
    }

    /** 
     * @param idVeiculo
     * @return List<Manutencao>
     */
    public List<Manutencao> listarManutencoesPorVeiculo(String idVeiculo) {
        return listarTodasManutencoes().stream()
                .filter(m -> m.getIdVeiculo().equals(idVeiculo))
                .collect(Collectors.toList());
    }
}