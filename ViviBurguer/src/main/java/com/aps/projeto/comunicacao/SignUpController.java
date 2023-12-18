package com.aps.projeto.comunicacao;
import static com.aps.projeto.negocio.converter.StatusConverter.toHttpStatus;

import com.aps.projeto.negocio.converter.StatusConverter;
import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.entity.ContaDTO;
import com.aps.projeto.negocio.mapper.ContaMapper;
import com.aps.projeto.negocio.pojos.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final Fachada fachada;

    @PostMapping("/conta/signUp")
    public ResponseEntity<String> efetuarSignUp(@RequestBody Conta conta) {
        BasicResponse response = fachada.efetuarSignUp(conta);
        return new ResponseEntity<>(response.getMessage(), toHttpStatus(response.getStatus()));
    }

    @GetMapping("/conta/get")
    public ResponseEntity<ContaDTO> exibirConta(@RequestParam String email) {
        Conta conta = fachada.exibirConta(email);
        if(conta != null) {
            return new ResponseEntity<>(ContaMapper.contaToContaDto(conta), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
