package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.MultaTransito;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MultaTransitoRepository implements IRepository<MultaTransito> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<MultaTransito> mapper;

    @Autowired
    public MultaTransitoRepository(ConfigMongoClient mongoClient, IImuttableMapper<MultaTransito> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
        this.mongoClient.connect();
    }

    @Override
    public void Create(MultaTransito multaTransito) {
        var collection = mongoClient.getCollection("MultasTransito");
        Document document = new Document()
                .append("idVeiculo", multaTransito.getIdVeiculo())
                .append("idContratoAluguel", multaTransito.getIdContratoAluguel())
                .append("codigoNotificacao", multaTransito.getCodigoNotificacao())
                .append("dataOcorrenciaInfracao", multaTransito.getDataOcorrenciaInfracao().toString())
                .append("dataNotificacaoLocadora", multaTransito.getDataNotificacaoLocadora().toString())
                .append("descricaoInfracao", multaTransito.getDescricaoInfracao())
                .append("orgaoAutuador", multaTransito.getOrgaoAutuador())
                .append("valorOriginalMulta", multaTransito.getValorOriginalMulta())
                .append("statusMulta", multaTransito.getStatusMulta().toString());

        collection.insertOne(document);
    }

    @Override
    public void Update(MultaTransito multaTransito) {
        var collection = mongoClient.getCollection("MultasTransito");
        Document document = new Document()
                .append("idVeiculo", multaTransito.getIdVeiculo())
                .append("idContratoAluguel", multaTransito.getIdContratoAluguel())
                .append("codigoNotificacao", multaTransito.getCodigoNotificacao())
                .append("dataOcorrenciaInfracao", multaTransito.getDataOcorrenciaInfracao().toString())
                .append("dataNotificacaoLocadora", multaTransito.getDataNotificacaoLocadora().toString())
                .append("descricaoInfracao", multaTransito.getDescricaoInfracao())
                .append("orgaoAutuador", multaTransito.getOrgaoAutuador())
                .append("valorOriginalMulta", multaTransito.getValorOriginalMulta())
                .append("statusMulta", multaTransito.getStatusMulta().toString());

        collection.updateOne(
                new Document("_id", new ObjectId(multaTransito.getIdMulta())),
                new Document("$set", document)
        );
    }

    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("MultasTransito");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public MultaTransito GetById(String id) {
        var collection = mongoClient.getCollection("MultasTransito");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    @Override
    public Iterable<MultaTransito> GetAll() {
        var collection = mongoClient.getCollection("MultasTransito");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
