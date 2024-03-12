package br.com.viviburguer.accountregister.controller;

import br.com.viviburguer.accountregister.negocio.Fachada;
import br.com.viviburguer.accountregister.negocio.exceptions.BadRequestException;
import br.com.viviburguer.accountregister.negocio.exceptions.ForbiddenException;
import br.com.viviburguer.accountregister.negocio.exceptions.NotFoundException;
import br.com.viviburguer.common.negocio.entity.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CadastroContaController {
    private final Fachada fachada;

    @PostMapping("/conta/existeConta")
    public ResponseEntity<Conta> existeConta(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        try {
            Conta response = fachada.efetuarSignIn(email, senha);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (BadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/conta/cadastrarConta")
    public ResponseEntity<Conta> cadastrarConta(@RequestBody Conta conta) {
        try {
            Conta response = fachada.efetuarSignUp(conta);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ForbiddenException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/conta/removerConta")
    public ResponseEntity<Boolean> removerConta(@RequestBody Conta conta) {
        try {
            Boolean response = fachada.removerConta(conta);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ForbiddenException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
