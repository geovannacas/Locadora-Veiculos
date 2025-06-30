package com.poo.domain.mappers;

import com.poo.domain.enums.StatusCobrancaEnum;
import com.poo.domain.enums.TipoCobrancaEnum;
import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.CobrancaAdicional;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CobrancaAdicionalMapper implements IImuttableMapper<CobrancaAdicional> {
    /** 
     * @param cobrancaAdicional
     * @return CobrancaAdicional
     */
    @Override
    public CobrancaAdicional Map(Document cobrancaAdicional) {
        if (cobrancaAdicional == null) {
            return null;
        }

        var cobrancaAdicionalModel = new CobrancaAdicional();
        cobrancaAdicionalModel.setId(cobrancaAdicional.getObjectId("_id").toHexString());
        cobrancaAdicionalModel.setIdContrato(cobrancaAdicional.getString("idContrato"));
        cobrancaAdicionalModel.setIdManutencao(cobrancaAdicional.getString("idManutencao"));
        cobrancaAdicionalModel.setIdMultaTransito(cobrancaAdicional.getString("idMultaTransito"));
        cobrancaAdicionalModel.setTipoCobranca(TipoCobrancaEnum.valueOf(cobrancaAdicional.getString("tipoCobranca")));
        cobrancaAdicionalModel.setDescricaoDetalhada(cobrancaAdicional.getString("descricaoDetalhada"));
        cobrancaAdicionalModel.setValorCobrado(cobrancaAdicional.getDouble("valorCobrado"));
        cobrancaAdicionalModel.setDataEmissaoCobranca(cobrancaAdicional.get("dataEmissaoCobranca", LocalDateTime.class));
        cobrancaAdicionalModel.setDataVencimentoPagamento(cobrancaAdicional.get("dataVencimentoPagamento", LocalDateTime.class));
        cobrancaAdicionalModel.setStatusCobranca(StatusCobrancaEnum.valueOf(cobrancaAdicional.getString("statusCobranca")));

        return cobrancaAdicionalModel;
    }
}
