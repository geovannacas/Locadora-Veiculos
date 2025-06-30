package com.poo.infraestructure.repositories;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.interfaces.IRepository;
import com.poo.domain.models.CobrancaAdicional;
import com.poo.infraestructure.ConfigMongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

@Service
public class CobrancaAdicionalRepository implements IRepository<CobrancaAdicional> {
    private final ConfigMongoClient mongoClient;
    private final IImuttableMapper<CobrancaAdicional> mapper;

    @Autowired
    public CobrancaAdicionalRepository(ConfigMongoClient mongoClient, IImuttableMapper<CobrancaAdicional> mapper) {
        this.mongoClient = mongoClient;
        this.mapper = mapper;
    }

    /** 
     * @param cobrancaAdicional
     */
    @Override
    public void Create(CobrancaAdicional cobrancaAdicional) {
        var collection = mongoClient.getCollection("CobrancasAdicionais");
        Document document = new Document()
                .append("idContrato", cobrancaAdicional.getIdContrato())
                .append("idManutencao", cobrancaAdicional.getIdManutencao())
                .append("idMultaTransito", cobrancaAdicional.getIdMultaTransito())
                .append("tipoCobranca", cobrancaAdicional.getTipoCobranca().toString())
                .append("descricaoDetalhada", cobrancaAdicional.getDescricaoDetalhada())
                .append("valorCobrado", cobrancaAdicional.getValorCobrado())
                .append("dataEmissaoCobranca", cobrancaAdicional.getDataEmissaoCobranca().toString())
                .append("dataVencimentoPagamento", cobrancaAdicional.getDataVencimentoPagamento().toString())
                .append("statusCobranca", cobrancaAdicional.getStatusCobranca().toString());

        collection.insertOne(document);
    }

    /** 
     * @param cobrancaAdicional
     */
    @Override
    public void Update(CobrancaAdicional cobrancaAdicional) {
        var collection = mongoClient.getCollection("CobrancasAdicionais");
        Document document = new Document()
                .append("idContrato", cobrancaAdicional.getIdContrato())
                .append("idManutencao", cobrancaAdicional.getIdManutencao())
                .append("idMultaTransito", cobrancaAdicional.getIdMultaTransito())
                .append("tipoCobranca", cobrancaAdicional.getTipoCobranca().toString())
                .append("descricaoDetalhada", cobrancaAdicional.getDescricaoDetalhada())
                .append("valorCobrado", cobrancaAdicional.getValorCobrado())
                .append("dataEmissaoCobranca", cobrancaAdicional.getDataEmissaoCobranca().toString())
                .append("dataVencimentoPagamento", cobrancaAdicional.getDataVencimentoPagamento().toString())
                .append("statusCobranca", cobrancaAdicional.getStatusCobranca().toString());

        collection.updateOne(
                new Document("_id", new ObjectId(cobrancaAdicional.getId())),
                new Document("$set", document)
        );
    }

    /** 
     * @param id
     */
    @Override
    public void Delete(String id) {
        var collection = mongoClient.getCollection("CobrancasAdicionais");
        collection.deleteOne(new Document("_id", new ObjectId(id)));
    }

    /** 
     * @param id
     * @return CobrancaAdicional
     */
    @Override
    public CobrancaAdicional GetById(String id) {
        var collection = mongoClient.getCollection("CobrancasAdicionais");
        if (collection != null) {
            return mapper.Map(collection.find(new Document("_id", new ObjectId(id))).first());
        }
        return null;
    }

    /** 
     * @return Iterable<CobrancaAdicional>
     */
    @Override
    public Iterable<CobrancaAdicional> GetAll() {
        var collection = mongoClient.getCollection("CobrancasAdicionais");
        if (collection != null) {
            var documents = collection.find().into(new ArrayList<>());
            return documents.stream()
                    .map(mapper::Map)
                    .toList();
        }
        return List.of();
    }
}
