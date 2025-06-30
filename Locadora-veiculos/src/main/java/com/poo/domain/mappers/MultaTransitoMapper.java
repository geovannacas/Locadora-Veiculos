package com.poo.domain.mappers;

import com.poo.domain.enums.StatusMultaEnum;
import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.MultaTransito;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MultaTransitoMapper implements IImuttableMapper<MultaTransito> {
    /** 
     * @param multaTransito
     * @return MultaTransito
     */
    @Override
    public MultaTransito Map(Document multaTransito) {
        if (multaTransito == null) {
            return null;
        }

        var multaTransitoModel = new MultaTransito();
        multaTransitoModel.setIdMulta(multaTransito.getObjectId("_id").toHexString());
        multaTransitoModel.setIdVeiculo(multaTransito.getString("idVeiculo"));
        multaTransitoModel.setIdContratoAluguel(multaTransito.getString("idContratoAluguel"));
        multaTransitoModel.setCodigoNotificacao(multaTransito.getString("codigoNotificacao"));
        multaTransitoModel.setDataOcorrenciaInfracao(multaTransito.get("dataOcorrenciaInfracao", LocalDateTime.class));
        multaTransitoModel.setDataNotificacaoLocadora(multaTransito.get("dataNotificacaoLocadora", LocalDate.class));
        multaTransitoModel.setDescricaoInfracao(multaTransito.getString("descricaoInfracao"));
        multaTransitoModel.setOrgaoAutuador(multaTransito.getString("orgaoAutuador"));
        multaTransitoModel.setValorOriginalMulta(multaTransito.getDouble("valorOriginalMulta"));
        multaTransitoModel.setStatusMulta(StatusMultaEnum.valueOf(multaTransito.getString("statusMulta")));

        return multaTransitoModel;
    }
}
