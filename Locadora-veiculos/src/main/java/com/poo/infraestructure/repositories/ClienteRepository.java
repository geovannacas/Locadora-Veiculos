package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Cliente;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteRepository implements IRepository<Cliente> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<Cliente> mapper;

    @Autowired
    public ClienteRepository(ConfigMongoClient mongoClient, IImuttableMapper<Cliente> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
    }

    /** 
     * @param cliente
     */
    @Override
    public void Create(Cliente cliente) {
        var collection = mongoClient.getCollection("Clientes");
        Document document = new Document()
                .append("nome", cliente.getNome())
                .append("email", cliente.getEmail())
                .append("telefone", cliente.getTelefone())
                .append("cpfCnpj", cliente.getCpfCnpj())
                .append("cnh", cliente.getCnh())
                .append("endereco", cliente.getEndereco());

        collection.insertOne(document);
    }

    /** 
     * @param cliente
     */
    @Override
    public void Update(Cliente cliente) {
        var collection = mongoClient.getCollection("Clientes");
        Document document = new Document()
                .append("nome", cliente.getNome())
                .append("email", cliente.getEmail())
                .append("telefone", cliente.getTelefone())
                .append("cpfCnpj", cliente.getCpfCnpj())
                .append("cnh", cliente.getCnh())
                .append("endereco", cliente.getEndereco());

        collection.updateOne(
                new Document("_id", new ObjectId(cliente.getId())),
                new Document("$set", document)
        );
    }

    /** 
     * @param id
     */
    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("Clientes");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    /** 
     * @param id
     * @return Cliente
     */
    @Override
    public Cliente GetById(String id) {
        var collection = mongoClient.getCollection("Clientes");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    /** 
     * @return Iterable<Cliente>
     */
    @Override
    public Iterable<Cliente> GetAll() {
        var collection = mongoClient.getCollection("Clientes");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
