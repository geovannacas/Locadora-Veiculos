package com.poo.domain.models;

import com.poo.domain.abstracts.Pessoa;
import com.poo.domain.enums.NivelAcessoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Funcionario extends Pessoa {
    private String cargo;
    private String login;
    private String senha;
    private NivelAcessoEnum nivelAcesso;

    @Override
    public String toString() {
        return "Funcionário ID: " + id + "\nNome: " + nome + "\nCargo: " + cargo + "\nLogin: " + login;
    }
}
