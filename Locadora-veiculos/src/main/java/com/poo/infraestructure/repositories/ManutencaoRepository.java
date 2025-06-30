package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.Manutencao;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManutencaoRepository implements IRepository<Manutencao> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<Manutencao> mapper;

    @Autowired
    public ManutencaoRepository(ConfigMongoClient mongoClient, IImuttableMapper<Manutencao> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
        this.mongoClient.connect();
    }

    @Override
    public void Create(Manutencao manutencao) {
        var collection = mongoClient.getCollection("Manutencoes");
        Document document = new Document()
                .append("idVeiculo", manutencao.getIdVeiculo())
                .append("dataAgendamento", manutencao.getDataAgendamento().toString())
                .append("dataRealizacao", manutencao.getDataRealizacao() != null ? manutencao.getDataRealizacao().toString() : null)
                .append("tipoManutencao", manutencao.getTipoManutencao().toString())
                .append("descricaoServico", manutencao.getDescricaoServico())
                .append("custo", manutencao.getCusto());

        collection.insertOne(document);
    }

    @Override
    public void Update(Manutencao manutencao) {
        var collection = mongoClient.getCollection("Manutencoes");
        Document document = new Document()
                .append("idVeiculo", manutencao.getIdVeiculo())
                .append("dataAgendamento", manutencao.getDataAgendamento().toString())
                .append("dataRealizacao", manutencao.getDataRealizacao() != null ? manutencao.getDataRealizacao().toString() : null)
                .append("tipoManutencao", manutencao.getTipoManutencao().toString())
                .append("descricaoServico", manutencao.getDescricaoServico())
                .append("custo", manutencao.getCusto());

        collection.updateOne(
                new Document("_id", new ObjectId(manutencao.getIdManutencao())),
                new Document("$set", document)
        );
    }

    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("Manutencoes");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public Manutencao GetById(String id) {
        var collection = mongoClient.getCollection("Manutencoes");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    @Override
    public Iterable<Manutencao> GetAll() {
        var collection = mongoClient.getCollection("Manutencoes");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
