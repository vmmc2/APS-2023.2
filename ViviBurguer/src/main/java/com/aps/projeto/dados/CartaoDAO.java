package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CartaoDAO extends CrudRepository<Cartao, Long> {

  boolean existsByCpf(CPF cpf);

  List<Cartao> findAllByCpf(CPF cpf);
}
