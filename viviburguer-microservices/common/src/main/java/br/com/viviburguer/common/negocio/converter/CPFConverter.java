package br.com.viviburguer.common.negocio.converter;

import br.com.viviburguer.common.negocio.pojos.CPF;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CPFConverter implements AttributeConverter<CPF, String> {

  @Override
  public String convertToDatabaseColumn(CPF cpf) {
    return (cpf != null) ? cpf.getCpf() : null;
  }

  @Override
  public CPF convertToEntityAttribute(String cpfString) {
    return (cpfString != null) ? new CPF(cpfString) : null;
  }
}