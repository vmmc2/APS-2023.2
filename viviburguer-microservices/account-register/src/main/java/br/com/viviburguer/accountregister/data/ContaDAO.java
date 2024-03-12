package br.com.viviburguer.accountregister.data;

import br.com.viviburguer.common.negocio.entity.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaDAO extends CrudRepository<Conta, Long> {
    Conta findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByEmailAndSenha(String email, String senha);

    void deleteByEmail(String email);
}