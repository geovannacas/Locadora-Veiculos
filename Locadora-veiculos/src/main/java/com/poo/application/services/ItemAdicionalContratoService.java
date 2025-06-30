package com.poo.application.services;

import com.poo.domain.models.ItemAdicionalContrato;
import com.poo.infraestructure.repositories.ItemAdicionalContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemAdicionalContratoService {

    private final ItemAdicionalContratoRepository itemAdicionalRepository;

    @Autowired
    public ItemAdicionalContratoService(ItemAdicionalContratoRepository itemAdicionalRepository) {
        this.itemAdicionalRepository = itemAdicionalRepository;
    }

    /** 
     * @param item
     */
    public void adicionarItemContrato(ItemAdicionalContrato item) {
        if (item.getIdContratoAluguel() == null || item.getIdContratoAluguel().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do contrato é obrigatório");
        }
        if (item.getDescricaoItem() == null || item.getDescricaoItem().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do item é obrigatória");
        }
        if (item.getValorItem() <= 0) {
            throw new IllegalArgumentException("Valor do item deve ser maior que zero");
        }
        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        itemAdicionalRepository.Create(item);
    }

    /** 
     * @param item
     */
    public void atualizarItemContrato(ItemAdicionalContrato item) {
        if (item.getIdItemAdicionalContrato() == null || item.getIdItemAdicionalContrato().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do item é obrigatório para atualização");
        }
        ItemAdicionalContrato existente = itemAdicionalRepository.GetById(item.getIdItemAdicionalContrato());
        if (existente == null) {
            throw new IllegalArgumentException("Item adicional não encontrado");
        }
        itemAdicionalRepository.Update(item);
    }

    /** 
     * @param id
     */
    public void removerItemContrato(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do item é obrigatório");
        }
        if (itemAdicionalRepository.GetById(id) == null) {
            throw new IllegalArgumentException("Item adicional não encontrado");
        }
        itemAdicionalRepository.Delete(id);
    }

    /** 
     * @param id
     * @return ItemAdicionalContrato
     */
    public ItemAdicionalContrato buscarItemPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do item é obrigatório");
        }
        return itemAdicionalRepository.GetById(id);
    }

    /** 
     * @param idContrato
     * @return List<ItemAdicionalContrato>
     */
    public List<ItemAdicionalContrato> listarItensPorContrato(String idContrato) {
        return ((List<ItemAdicionalContrato>) itemAdicionalRepository.GetAll()).stream()
                .filter(item -> item.getIdContratoAluguel().equals(idContrato))
                .collect(Collectors.toList());
    }
}