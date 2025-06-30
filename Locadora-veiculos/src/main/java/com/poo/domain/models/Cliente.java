package com.poo.domain.models;

import com.poo.domain.abstracts.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter
@Getter
@Document
public class Cliente extends Pessoa {
    private String cpfCnpj;
    private String cnh;
    private String endereco;

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + getId() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", cnh='" + cnh + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
