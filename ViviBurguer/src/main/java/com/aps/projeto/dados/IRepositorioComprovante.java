package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;

public interface IRepositorioComprovante {
  List<Comprovante> encontrar(CPF cpf);
  boolean registrarComprovante(Comprovante comprovante);
}
