package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Veiculo;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoRepository implements IRepository<Veiculo> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<Veiculo> mapper;

    @Autowired
    public VeiculoRepository(ConfigMongoClient mongoClient, IImuttableMapper<Veiculo> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
        this.mongoClient.connect();
    }

    @Override
    public void Create(Veiculo veiculo) {
        var collection = mongoClient.getCollection("Veiculos");
        Document document = new Document()
                .append("placa", veiculo.getPlaca())
                .append("modelo", veiculo.getModelo())
                .append("marca", veiculo.getMarca())
                .append("ano", veiculo.getAno())
                .append("idCategoria", veiculo.getIdCategoria())
                .append("status", veiculo.getStatus().toString())
                .append("kmAtual", veiculo.getKmAtual());

        collection.insertOne(document);
    }

    @Override
    public void Update(Veiculo veiculo) {
        var collection = mongoClient.getCollection("Veiculos");
        Document document = new Document()
                .append("placa", veiculo.getPlaca())
                .append("modelo", veiculo.getModelo())
                .append("marca", veiculo.getMarca())
                .append("ano", veiculo.getAno())
                .append("idCategoria", veiculo.getIdCategoria())
                .append("status", veiculo.getStatus().toString())
                .append("kmAtual", veiculo.getKmAtual());

        collection.updateOne(
                new Document("_id", new ObjectId(veiculo.getIdVeiculo())),
                new Document("$set", document)
        );
    }

    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("Veiculos");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public Veiculo GetById(String id) {
        var collection = mongoClient.getCollection("Veiculos");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    @Override
    public Iterable<Veiculo> GetAll() {
        var collection = mongoClient.getCollection("Veiculos");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
