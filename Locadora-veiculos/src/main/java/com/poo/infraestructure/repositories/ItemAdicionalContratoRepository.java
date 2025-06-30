package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.ItemAdicionalContrato;
import com.poo.infraestructure.ConfigMongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemAdicionalContratoRepository implements IRepository<ItemAdicionalContrato> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<ItemAdicionalContrato> mapper;

    @Autowired
    public ItemAdicionalContratoRepository(ConfigMongoClient mongoClient, IImuttableMapper<ItemAdicionalContrato> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
        this.mongoClient.connect();
    }

    @Override
    public void Create(ItemAdicionalContrato itemAdicionalContrato) {
        var collection = mongoClient.getCollection("ItensAdicionaisContrato");
        Document document = new Document()
                .append("descricaoItem", itemAdicionalContrato.getDescricaoItem())
                .append("valorItem", itemAdicionalContrato.getValorItem())
                .append("quantidade", itemAdicionalContrato.getQuantidade())
                .append("idContratoAluguel", itemAdicionalContrato.getIdContratoAluguel());

        collection.insertOne(document);
    }

    @Override
    public void Update(ItemAdicionalContrato itemAdicionalContrato) {
        var collection = mongoClient.getCollection("ItensAdicionaisContrato");
        Document document = new Document()
                .append("descricaoItem", itemAdicionalContrato.getDescricaoItem())
                .append("valorItem", itemAdicionalContrato.getValorItem())
                .append("quantidade", itemAdicionalContrato.getQuantidade())
                .append("idContratoAluguel", itemAdicionalContrato.getIdContratoAluguel());

        collection.updateOne(
                new Document("_id", new ObjectId(itemAdicionalContrato.getIdItemAdicionalContrato())),
                new Document("$set", document)
        );
    }

    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("ItensAdicionaisContrato");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public ItemAdicionalContrato GetById(String id) {
        var collection = mongoClient.getCollection("ItensAdicionaisContrato");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    @Override
    public Iterable<ItemAdicionalContrato> GetAll() {
        var collection = mongoClient.getCollection("ItensAdicionaisContrato");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
