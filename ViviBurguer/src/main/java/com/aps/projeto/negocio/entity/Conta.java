package com.aps.projeto.negocio.entity;
import com.aps.projeto.negocio.converter.CPFConverter;
import com.aps.projeto.negocio.pojos.CPF;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Column(unique = true)
    @Convert(converter = CPFConverter.class)
    private CPF cpf;
    private String telefone;
    @Column(unique = true)
    private String email;
    private String senha;
    private String endereco;
}
