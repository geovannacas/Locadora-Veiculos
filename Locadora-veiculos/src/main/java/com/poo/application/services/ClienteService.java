package com.poo.application.services;

import com.poo.domain.models.Cliente;
import com.poo.infraestructure.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /** 
     * @param cliente
     */
    public void criarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        if (cliente.getCpfCnpj() == null || cliente.getCpfCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF/CNPJ é obrigatório");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }
        if (cliente.getCnh() == null || cliente.getCnh().trim().isEmpty()) {
            throw new IllegalArgumentException("CNH é obrigatório");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório");
        }
        clienteRepository.Create(cliente);
    }

    /** 
     * @param cliente
     */
    public void atualizarCliente(Cliente cliente) {
        if (cliente.getId() == null || cliente.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do cliente é obrigatório para atualização");
        }
        Cliente clienteExistente = clienteRepository.GetById(cliente.getId());
        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clienteRepository.Update(cliente);
    }

    /** 
     * @param id
     */
    public void removerCliente(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        Cliente cliente = clienteRepository.GetById(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clienteRepository.Delete(id);
    }

    /** 
     * @param id
     * @return Cliente
     */
    public Cliente buscarClientePorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        return clienteRepository.GetById(id);
    }

    /** 
     * @return List<Cliente>
     */
    public List<Cliente> listarTodosClientes() {
        return (List<Cliente>) clienteRepository.GetAll();
    }
}
