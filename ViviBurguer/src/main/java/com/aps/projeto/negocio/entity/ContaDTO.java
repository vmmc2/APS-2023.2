package com.aps.projeto.negocio.entity;

import com.aps.projeto.negocio.converter.CPFConverter;
import com.aps.projeto.negocio.pojos.CPF;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private String senha;
  private String endereco;
}
