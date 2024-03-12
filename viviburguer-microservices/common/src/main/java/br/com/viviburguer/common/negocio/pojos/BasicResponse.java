package br.com.viviburguer.common.negocio.pojos;

import br.com.viviburguer.common.negocio.enumerators.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
public class BasicResponse {
    private String message;
    private Status status;
}
