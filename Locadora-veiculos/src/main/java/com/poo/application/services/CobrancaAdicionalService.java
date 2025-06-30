package com.poo.application.services;

import com.poo.domain.enums.StatusCobrancaEnum;
import com.poo.domain.models.CobrancaAdicional;
import com.poo.infraestructure.repositories.CobrancaAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CobrancaAdicionalService {

    private final CobrancaAdicionalRepository cobrancaAdicionalRepository;

    @Autowired
    public CobrancaAdicionalService(CobrancaAdicionalRepository cobrancaAdicionalRepository) {
        this.cobrancaAdicionalRepository = cobrancaAdicionalRepository;
    }

    /** 
     * @param cobranca
     */
    public void criarCobrancaAdicional(CobrancaAdicional cobranca) {
        if (cobranca.getIdContrato() == null || cobranca.getIdContrato().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório");
        }
        if (cobranca.getValorCobrado() == null || cobranca.getValorCobrado() <= 0) {
            throw new IllegalArgumentException("O valor da cobrança deve ser maior que zero");
        }
        if (cobranca.getTipoCobranca() == null) {
            throw new IllegalArgumentException("O tipo da cobrança é obrigatório");
        }
        if (cobranca.getDataEmissaoCobranca() == null) {
            cobranca.setDataEmissaoCobranca(LocalDateTime.now());
        }
        if (cobranca.getStatusCobranca() == null) {
            cobranca.setStatusCobranca(StatusCobrancaEnum.PENDENTE);
        }
        cobrancaAdicionalRepository.Create(cobranca);
    }

    /** 
     * @param cobranca
     */
    public void atualizarCobrancaAdicional(CobrancaAdicional cobranca) {
        if (cobranca.getId() == null || cobranca.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID da cobrança é obrigatório para atualização");
        }
        CobrancaAdicional existente = cobrancaAdicionalRepository.GetById(cobranca.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Cobrança adicional não encontrada");
        }
        cobrancaAdicionalRepository.Update(cobranca);
    }

    /** 
     * @param id
     */
    public void removerCobrancaAdicional(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da cobrança é obrigatório");
        }
        if (cobrancaAdicionalRepository.GetById(id) == null) {
            throw new IllegalArgumentException("Cobrança adicional não encontrada");
        }
        cobrancaAdicionalRepository.Delete(id);
    }

    /** 
     * @param id
     * @return CobrancaAdicional
     */
    public CobrancaAdicional buscarCobrancaPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da cobrança é obrigatório");
        }
        return cobrancaAdicionalRepository.GetById(id);
    }

    /** 
     * @return List<CobrancaAdicional>
     */
    public List<CobrancaAdicional> listarTodasCobrancas() {
        return (List<CobrancaAdicional>) cobrancaAdicionalRepository.GetAll();
    }

    /** 
     * @param idContrato
     * @return List<CobrancaAdicional>
     */
    public List<CobrancaAdicional> listarCobrancasPorContrato(String idContrato) {
        return listarTodasCobrancas().stream()
                .filter(c -> c.getIdContrato().equals(idContrato))
                .collect(Collectors.toList());
    }
}
