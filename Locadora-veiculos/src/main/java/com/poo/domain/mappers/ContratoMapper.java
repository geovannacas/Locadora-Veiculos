package com.poo.domain.mappers;

import com.poo.domain.enums.StatusContratoEnum;
import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.Contrato;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper implements IImuttableMapper<Contrato> {
    /** 
     * @param data
     * @return Contrato
     */
    @Override
    public Contrato Map(Document data) {
        var contrato = new Contrato();
        contrato.setIdContrato(data.getString("idContrato"));
        contrato.setIdCliente(data.getString("idCliente"));
        contrato.setIdVeiculo(data.getString("idVeiculo"));
        contrato.setDataHoraRetiradaPrevista(data.getDate("dataHoraRetiradaPrevista").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
        contrato.setDataHoraDevolucaoPrevista(data.getDate("dataHoraDevolucaoPrevista").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
        contrato.setDataHoraRetiradaReal(data.getDate("dataHoraRetiradaReal") != null ? data.getDate("dataHoraRetiradaReal").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null);
        contrato.setDataHoraDevolucaoReal(data.getDate("dataHoraDevolucaoReal") != null ? data.getDate("dataHoraDevolucaoReal").toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null);
        contrato.setValorTotalPrevisto(data.getDouble("valorTotalPrevisto"));
        contrato.setStatusContrato(StatusContratoEnum.valueOf(data.getString("statusContrato")));
        return contrato;
    }
}
