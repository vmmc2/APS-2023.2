package br.com.viviburguer.common.negocio.entity;

import br.com.viviburguer.common.negocio.converter.CPFConverter;
import br.com.viviburguer.common.negocio.pojos.CPF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
