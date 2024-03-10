package com.aps.projeto.comunicacao.Observer;

import com.aps.projeto.negocio.entity.Comprovante;

public interface IEventListener {

  void update(String eventType, Comprovante file);
}
