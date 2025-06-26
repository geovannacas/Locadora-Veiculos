package com.poo.domain.abstracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {
    @Id
    protected String id;
    protected String nome;
    protected String email;
    protected String telefone;
}
