package com.poo.domain.mappers;

import com.poo.domain.enums.TipoManutencaoEnum;
import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.Manutencao;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ManutencaoMapper implements IImuttableMapper<Manutencao> {
    @Override
    public Manutencao Map(Document manutencao) {
        if (manutencao == null) {
            return null;
        }

        var manutencaoModel = new Manutencao();
        manutencaoModel.setIdManutencao(manutencao.getObjectId("_id").toHexString());
        manutencaoModel.setIdVeiculo(manutencao.getString("idVeiculo"));
        manutencaoModel.setDataAgendamento(manutencao.get("dataAgendamento", java.time.LocalDate.class));
        manutencaoModel.setDataRealizacao(manutencao.get("dataRealizacao", java.time.LocalDate.class));
        manutencaoModel.setTipoManutencao(TipoManutencaoEnum.valueOf(manutencao.getString("tipoManutencao")));
        manutencaoModel.setDescricaoServico(manutencao.getString("descricaoServico"));
        manutencaoModel.setCusto(manutencao.getDouble("custo"));

        return manutencaoModel;
    }
}
