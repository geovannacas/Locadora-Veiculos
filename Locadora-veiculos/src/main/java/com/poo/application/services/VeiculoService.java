package com.poo.application.services;

import com.poo.domain.models.Veiculo;
import com.poo.domain.enums.StatusVeiculoEnum;
import com.poo.infraestructure.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    /** 
     * @param veiculo
     */
    public void criarVeiculo(Veiculo veiculo) {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo é obrigatória");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo é obrigatório");
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("Marca do veículo é obrigatória");
        }
        if (veiculo.getStatus() == null) {
            veiculo.setStatus(StatusVeiculoEnum.DISPONIVEL);
        }
        veiculoRepository.Create(veiculo);
    }

    /** 
     * @param veiculo
     */
    public void atualizarVeiculo(Veiculo veiculo) {
        if (veiculo.getIdVeiculo() == null || veiculo.getIdVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do veículo é obrigatório para atualização");
        }
        Veiculo veiculoExistente = veiculoRepository.GetById(veiculo.getIdVeiculo());
        if (veiculoExistente == null) {
            throw new IllegalArgumentException("Veículo não encontrado");
        }
        veiculoRepository.Update(veiculo);
    }

    /** 
     * @param id
     */
    public void removerVeiculo(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do veículo é obrigatório");
        }
        Veiculo veiculo = veiculoRepository.GetById(id);
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado");
        }
        veiculoRepository.Delete(id);
    }

    /** 
     * @param id
     * @return Veiculo
     */
    public Veiculo buscarVeiculoPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do veículo é obrigatório");
        }
        return veiculoRepository.GetById(id);
    }

    /** 
     * @return List<Veiculo>
     */
    public List<Veiculo> listarTodosVeiculos() {
        return (List<Veiculo>) veiculoRepository.GetAll();
    }

    /** 
     * @param id
     * @param novoStatus
     */
    public void atualizarStatusVeiculo(String id, StatusVeiculoEnum novoStatus) {
        Veiculo veiculo = buscarVeiculoPorId(id);
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado");
        }
        veiculo.setStatus(novoStatus);
        veiculoRepository.Update(veiculo);
    }

    /** 
     * @return List<Veiculo>
     */
    public List<Veiculo> listarVeiculosDisponiveis() {
        return listarTodosVeiculos().stream()
                .filter(v -> v.getStatus() == StatusVeiculoEnum.DISPONIVEL)
                .toList();
    }
}
