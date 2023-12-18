package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.pojos.CPF;
import org.springframework.data.repository.CrudRepository;

public interface CartaoDAO extends CrudRepository<Cartao, Long> {

  boolean existsByCpf(CPF cpf);
}
