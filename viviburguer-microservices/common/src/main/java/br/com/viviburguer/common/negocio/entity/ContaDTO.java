package br.com.viviburguer.common.negocio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  public static class Builder {
    private ContaDTO contaDTO;

    public Builder() {
      contaDTO = new ContaDTO();
    }
    public Builder nome(String nome) {
      contaDTO.nome = nome;
      return this;
    }
    public Builder cpf(String cpf) {
      contaDTO.cpf = cpf;
      return this;
    }

    public Builder telefone(String telefone) {
      contaDTO.telefone = telefone;
      return this;
    }

    public Builder email(String email) {
      contaDTO.email = email;
      return this;
    }

    public Builder senha(String senha) {
      contaDTO.senha = senha;
      return this;
    }

    public Builder endereco(String endereco) {
      contaDTO.endereco = endereco;
      return this;
    }

    // Método para construir o objeto ContaDTO
    public ContaDTO build() {
      return contaDTO;
    }
  }
}
