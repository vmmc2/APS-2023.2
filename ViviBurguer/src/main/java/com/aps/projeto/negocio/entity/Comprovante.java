package com.aps.projeto.negocio.entity;

import com.aps.projeto.negocio.converter.CPFConverter;
import com.aps.projeto.negocio.enumerators.Status;
import com.aps.projeto.negocio.pojos.CPF;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comprovante {
  @Id
  private UUID id;
  private String nomeUsuario;
  @Convert(converter = CPFConverter.class)
  private CPF cpf;
  private BigDecimal valorPagamento;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Item> itens;
  private LocalDateTime dataOperacao;

  private String message;
  private Status status;
}
