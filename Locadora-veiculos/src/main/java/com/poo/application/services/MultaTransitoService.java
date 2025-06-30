package com.poo.application.services;

import com.poo.domain.enums.StatusMultaEnum;
import com.poo.domain.models.MultaTransito;
import com.poo.infraestructure.repositories.MultaTransitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MultaTransitoService {

    private final MultaTransitoRepository multaTransitoRepository;

    @Autowired
    public MultaTransitoService(MultaTransitoRepository multaTransitoRepository) {
        this.multaTransitoRepository = multaTransitoRepository;
    }

    /** 
     * @param multa
     */
    public void registrarMulta(MultaTransito multa) {
        if (multa.getIdContratoAluguel() == null || multa.getIdContratoAluguel().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório");
        }
        if (multa.getValorOriginalMulta() == null || multa.getValorOriginalMulta() <= 0) {
            throw new IllegalArgumentException("Valor da multa é obrigatório");
        }
        if (multa.getDataOcorrenciaInfracao() == null) {
            throw new IllegalArgumentException("Data da infração é obrigatória");
        }
        if (multa.getStatusMulta() == null) {
            multa.setStatusMulta(StatusMultaEnum.PENDENTE_IDENTIFICACAO_CONDUTOR);
        }
        multaTransitoRepository.Create(multa);
    }

    /** 
     * @param multa
     */
    public void atualizarMulta(MultaTransito multa) {
        if (multa.getIdMulta() == null || multa.getIdMulta().trim().isEmpty()) {
            throw new IllegalArgumentException("ID da multa é obrigatório para atualização");
        }
        MultaTransito existente = multaTransitoRepository.GetById(multa.getIdMulta());
        if (existente == null) {
            throw new IllegalArgumentException("Multa não encontrada");
        }
        multaTransitoRepository.Update(multa);
    }

    /** 
     * @param id
     */
    public void removerMulta(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da multa é obrigatório");
        }
        if (multaTransitoRepository.GetById(id) == null) {
            throw new IllegalArgumentException("Multa não encontrada");
        }
        multaTransitoRepository.Delete(id);
    }

    /** 
     * @param id
     * @return MultaTransito
     */
    public MultaTransito buscarMultaPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da multa é obrigatório");
        }
        return multaTransitoRepository.GetById(id);
    }

    /** 
     * @return List<MultaTransito>
     */
    public List<MultaTransito> listarTodasMultas() {
        return (List<MultaTransito>) multaTransitoRepository.GetAll();
    }

    /** 
     * @param idContrato
     * @return List<MultaTransito>
     */
    public List<MultaTransito> listarMultasPorContrato(String idContrato) {
        return listarTodasMultas().stream()
                .filter(m -> m.getIdContratoAluguel().equals(idContrato))
                .collect(Collectors.toList());
    }
}