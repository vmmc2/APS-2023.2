package com.aps.projeto.comunicacao.Observer;

import com.aps.projeto.negocio.entity.Comprovante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class EventManager {
  Map<String, List<IEventListener>> listeners = new HashMap<>();
  public void subscribe(String eventType, IEventListener listener) {
    if(!this.listeners.containsKey(eventType)) {
      this.listeners.put(eventType, new ArrayList<>());
    }
    List<IEventListener> users = listeners.get(eventType);
    users.add(listener);
  }

  public void unsubscribe(String eventType, IEventListener listener) {
    List<IEventListener> users = listeners.get(eventType);
    users.remove(listener);
  }

  public void notify(String eventType, Comprovante comprovante) {
    List<IEventListener> users = listeners.get(eventType);
    for (IEventListener listener : users) {
      listener.update(eventType, comprovante);
    }
  }

}
