package com.poo.domain.mappers;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.Cliente;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper implements IImuttableMapper<Cliente> {
    /** 
     * @param cliente
     * @return Cliente
     */
    @Override
    public Cliente Map(Document cliente) {
        if (cliente == null) {
            return null;
        }

        var clienteModel = new Cliente();
        clienteModel.setId(cliente.getObjectId("_id").toHexString());
        clienteModel.setNome(cliente.getString("nome"));
        clienteModel.setEmail(cliente.getString("email"));
        clienteModel.setTelefone(cliente.getString("telefone"));
        clienteModel.setCpfCnpj(cliente.getString("cpfCnpj"));
        clienteModel.setCnh(cliente.getString("cnh"));
        clienteModel.setEndereco(cliente.getString("endereco"));
        return clienteModel;
    }
}
