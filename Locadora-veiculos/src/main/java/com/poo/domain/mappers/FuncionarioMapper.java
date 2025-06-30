package com.poo.domain.mappers;

import com.poo.domain.enums.NivelAcessoEnum;
import com.poo.domain.interfaces.IImuttableMapper;
import com.poo.domain.models.Funcionario;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper implements IImuttableMapper<Funcionario> {
    /** 
     * @param funcionario
     * @return Funcionario
     */
    @Override
    public Funcionario Map(Document funcionario) {
        if (funcionario == null) {
            return null;
        }

        var funcionarioModel = new Funcionario();
        funcionarioModel.setId(funcionario.getObjectId("_id").toHexString());
        funcionarioModel.setNome(funcionario.getString("nome"));
        funcionarioModel.setEmail(funcionario.getString("email"));
        funcionarioModel.setTelefone(funcionario.getString("telefone"));
        funcionarioModel.setCargo(funcionario.getString("cargo"));
        funcionarioModel.setLogin(funcionario.getString("login"));
        funcionarioModel.setSenha(funcionario.getString("senha"));
        funcionarioModel.setNivelAcesso(NivelAcessoEnum.valueOf(funcionario.getString("nivelAcesso")));

        return funcionarioModel;
    }
}
