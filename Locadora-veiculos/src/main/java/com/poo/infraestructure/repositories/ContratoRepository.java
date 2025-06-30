package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Contrato;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContratoRepository implements IRepository<Contrato> {

    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<Contrato> mapper;

    @Autowired
    public ContratoRepository(ConfigMongoClient mongoClient, IImuttableMapper<Contrato> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
        this.mongoClient.connect();
    }

    public void Create(Contrato contrato) {
        var collection = mongoClient.getCollection("Contratos");
        Document document = new Document()
                .append("idCliente", contrato.getIdCliente())
                .append("idVeiculo", contrato.getIdVeiculo())
                .append("dataHoraRetiradaPrevista", contrato.getDataHoraRetiradaPrevista().toString())
                .append("dataHoraDevolucaoPrevista", contrato.getDataHoraDevolucaoPrevista().toString())
                .append("dataHoraRetiradaReal", contrato.getDataHoraRetiradaReal() != null ? contrato.getDataHoraRetiradaReal().toString() : null)
                .append("dataHoraDevolucaoReal", contrato.getDataHoraDevolucaoReal() != null ? contrato.getDataHoraDevolucaoReal().toString() : null)
                .append("valorTotalPrevisto", contrato.getValorTotalPrevisto())
                .append("statusContrato", contrato.getStatusContrato().toString());

        collection.insertOne(document);
    }

    public void Update(Contrato contrato) {
        var collection = mongoClient.getCollection("Contratos");
        Document document = new Document()
                .append("idCliente", contrato.getIdCliente())
                .append("idVeiculo", contrato.getIdVeiculo())
                .append("dataHoraRetiradaPrevista", contrato.getDataHoraRetiradaPrevista().toString())
                .append("dataHoraDevolucaoPrevista", contrato.getDataHoraDevolucaoPrevista().toString())
                .append("dataHoraRetiradaReal", contrato.getDataHoraRetiradaReal() != null ? contrato.getDataHoraRetiradaReal().toString() : null)
                .append("dataHoraDevolucaoReal", contrato.getDataHoraDevolucaoReal() != null ? contrato.getDataHoraDevolucaoReal().toString() : null)
                .append("valorTotalPrevisto", contrato.getValorTotalPrevisto())
                .append("statusContrato", contrato.getStatusContrato().toString());

        collection.updateOne(
                new Document("_id", new ObjectId(contrato.getIdContrato())),
                new Document("$set", document)
        );
    }

    public void Delete(String id) {
        var collection = mongoClient.getCollection("Contratos");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    public Contrato GetById(String id) {
        var collection = mongoClient.getCollection("Contratos");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    public Iterable<Contrato> GetAll() {
        var collection = mongoClient.getCollection("Contratos");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
