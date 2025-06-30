package com.poo.domain.mappers;

import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.ItemAdicionalContrato;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ItemAdicionalMapper implements IImuttableMapper<ItemAdicionalContrato> {
    public ItemAdicionalContrato Map(Document itemAdicional) {
        if (itemAdicional == null) {
            return null;
        }

        var itemAdicionalContrato = new ItemAdicionalContrato();
        itemAdicionalContrato.setIdItemAdicionalContrato(itemAdicional.getObjectId("_id").toHexString());
        itemAdicionalContrato.setDescricaoItem(itemAdicional.getString("descricaoItem"));
        itemAdicionalContrato.setValorItem(itemAdicional.getDouble("valorItem"));
        itemAdicionalContrato.setQuantidade(itemAdicional.getInteger("quantidade"));
        itemAdicionalContrato.setIdContratoAluguel(itemAdicional.getString("idContratoAluguel"));

        return itemAdicionalContrato;
    }
}
