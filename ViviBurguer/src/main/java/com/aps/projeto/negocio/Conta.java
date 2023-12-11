package com.aps.projeto.negocio;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conta {
    @Id
    private long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String endereco;
}
