package com.poo.application.services;

import com.poo.domain.models.Contrato;
import com.poo.domain.enums.StatusContratoEnum;
import com.poo.infraestructure.repositories.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;

    @Autowired
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    /** 
     * @param contrato
     */
    public void criarContrato(Contrato contrato) {
        if (contrato.getIdCliente() == null || contrato.getIdCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        if (contrato.getIdVeiculo() == null || contrato.getIdVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do veículo é obrigatório");
        }
        if (contrato.getDataHoraRetiradaPrevista() == null) {
            throw new IllegalArgumentException("Data/hora de retirada prevista é obrigatória");
        }
        if (contrato.getDataHoraDevolucaoPrevista() == null) {
            throw new IllegalArgumentException("Data/hora de devolução prevista é obrigatória");
        }
        if (contrato.getStatusContrato() == null) {
            contrato.setStatusContrato(StatusContratoEnum.ABERTO);
        }
        contratoRepository.Create(contrato);
    }

    /** 
     * @param contrato
     */
    public void atualizarContrato(Contrato contrato) {
        if (contrato.getIdContrato() == null || contrato.getIdContrato().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório para atualização");
        }
        Contrato contratoExistente = contratoRepository.GetById(contrato.getIdContrato());
        if (contratoExistente == null) {
            throw new IllegalArgumentException("Contrato não encontrado");
        }
        contratoRepository.Update(contrato);
    }

    /** 
     * @param id
     */
    public void removerContrato(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório");
        }
        Contrato contrato = contratoRepository.GetById(id);
        if (contrato == null) {
            throw new IllegalArgumentException("Contrato não encontrado");
        }
        contratoRepository.Delete(id);
    }

    /** 
     * @param id
     * @return Contrato
     */
    public Contrato buscarContratoPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório");
        }
        return contratoRepository.GetById(id);
    }

    /** 
     * @return List<Contrato>
     */
    public List<Contrato> listarTodosContratos() {
        return (List<Contrato>) contratoRepository.GetAll();
    }

    /** 
     * @param id
     * @param novoStatus
     */
    public void atualizarStatusContrato(String id, StatusContratoEnum novoStatus) {
        Contrato contrato = buscarContratoPorId(id);
        if (contrato == null) {
            throw new IllegalArgumentException("Contrato não encontrado");
        }
        contrato.setStatusContrato(novoStatus);
        contratoRepository.Update(contrato);
    }

    /** 
     * @param id
     * @param dataHoraDevolucaoReal
     */
    public void finalizarContrato(String id, LocalDateTime dataHoraDevolucaoReal) {
        Contrato contrato = buscarContratoPorId(id);
        if (contrato == null) {
            throw new IllegalArgumentException("Contrato não encontrado");
        }
        contrato.setDataHoraDevolucaoReal(dataHoraDevolucaoReal);
        contrato.setStatusContrato(StatusContratoEnum.FECHADO);
        contratoRepository.Update(contrato);
    }

    /** 
     * @return List<Contrato>
     */
    public List<Contrato> listarContratosAbertos() {
        return listarTodosContratos().stream()
                .filter(c -> c.getStatusContrato() == StatusContratoEnum.ABERTO)
                .toList();
    }

    /** 
     * @param idCliente
     * @return List<Contrato>
     */
    public List<Contrato> listarContratosPorCliente(String idCliente) {
        return listarTodosContratos().stream()
                .filter(c -> c.getIdCliente().equals(idCliente))
                .toList();
    }
}
