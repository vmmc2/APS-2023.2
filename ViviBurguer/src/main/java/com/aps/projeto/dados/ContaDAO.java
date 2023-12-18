package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaDAO extends CrudRepository<Conta, Long> {
    Conta findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
