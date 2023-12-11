package com.aps.projeto.negocio;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContaRequest {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String endereco;
}

