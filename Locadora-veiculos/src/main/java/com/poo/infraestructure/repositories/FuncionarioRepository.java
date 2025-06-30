package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Funcionario;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioRepository implements IRepository<Funcionario> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<Funcionario> mapper;

    @Autowired
    public FuncionarioRepository(ConfigMongoClient mongoClient, IImuttableMapper<Funcionario> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
        this.mongoClient.connect();
    }

    @Override
    public void Create(Funcionario funcionario) {
        var collection = mongoClient.getCollection("Funcionarios");
        Document document = new Document()
                .append("nome", funcionario.getNome())
                .append("telefone", funcionario.getTelefone())
                .append("email", funcionario.getEmail())
                .append("cargo", funcionario.getCargo())
                .append("login", funcionario.getLogin())
                .append("senha", funcionario.getSenha())
                .append("nivelAcesso", funcionario.getNivelAcesso().toString());

        collection.insertOne(document);
    }

    @Override
    public void Update(Funcionario funcionario) {
        var collection = mongoClient.getCollection("Funcionarios");
        Document document = new Document()
                .append("nome", funcionario.getNome())
                .append("telefone", funcionario.getTelefone())
                .append("email", funcionario.getEmail())
                .append("cargo", funcionario.getCargo())
                .append("login", funcionario.getLogin())
                .append("senha", funcionario.getSenha())
                .append("nivelAcesso", funcionario.getNivelAcesso().toString());

        collection.updateOne(
                new Document("_id", new ObjectId(funcionario.getId())),
                new Document("$set", document)
        );
    }

    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("Funcionarios");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public Funcionario GetById(String id) {
        var collection = mongoClient.getCollection("Funcionarios");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    @Override
    public Iterable<Funcionario> GetAll() {
        var collection = mongoClient.getCollection("Funcionarios");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
