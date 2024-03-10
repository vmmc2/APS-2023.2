package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ComprovanteDAO extends CrudRepository<Comprovante, UUID> {
  List<Comprovante> findAllByCpf(CPF cpf);
}
