package com.poo.domain.mappers;

import com.poo.domain.enums.StatusVeiculoEnum;
import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.Veiculo;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper implements IImuttableMapper<Veiculo> {
    /** 
     * @param veiculo
     * @return Veiculo
     */
    @Override
    public Veiculo Map(Document veiculo) {
        if (veiculo == null) {
            return null;
        }

        var veiculoModel = new Veiculo();
        veiculoModel.setIdVeiculo(veiculo.getObjectId("_id").toHexString());
        veiculoModel.setPlaca(veiculo.getString("placa"));
        veiculoModel.setModelo(veiculo.getString("modelo"));
        veiculoModel.setMarca(veiculo.getString("marca"));
        veiculoModel.setAno(veiculo.getInteger("ano"));
        veiculoModel.setIdCategoria(veiculo.getString("idCategoria"));
        veiculoModel.setStatus(StatusVeiculoEnum.valueOf(veiculo.getString("status")));
        veiculoModel.setKmAtual(veiculo.getDouble("kmAtual"));

        return veiculoModel;
    }
}
