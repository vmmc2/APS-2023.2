package br.com.aps.viviburguer.account.controller;

import br.com.aps.viviburguer.account.facade.AccountRegisterFacade;
import br.com.aps.viviburguer.account.security.JwtTokenProvider;
import br.com.viviburguer.common.negocio.entity.Conta;
import br.com.viviburguer.common.negocio.pojos.BasicResponse;
import br.com.viviburguer.common.negocio.pojos.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.viviburguer.common.negocio.converter.StatusConverter.toHttpStatus;

@RestController
public class AccountController {
    private final AccountRegisterFacade accountRegisterFacade;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    AccountController(AccountRegisterFacade accountRegisterFacade, JwtTokenProvider jwtTokenProvider) {
        this.accountRegisterFacade = accountRegisterFacade;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/conta/signIn")
    public ResponseEntity<SignInResponse> efetuarSignIn(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        SignInResponse signInResponse = accountRegisterFacade.efetuarSignIn(email, senha);
        String token = jwtTokenProvider.generateToken(email);
        HttpHeaders headers = new HttpHeaders();
        if(signInResponse.getStatus().equals(HttpStatus.OK)) {
            headers.add("Authorization", "Bearer " + token);
        }
        return new ResponseEntity<>(signInResponse, headers, signInResponse.getStatus());
    }

    @PostMapping("/conta/signUp")
    public ResponseEntity<String> efetuarSignUp(@RequestBody Conta conta) {
        BasicResponse response = accountRegisterFacade.efetuarSignUp(conta);
        return new ResponseEntity<>(response.getMessage(), toHttpStatus(response.getStatus()));
    }

    @DeleteMapping("/conta/remover")
    public ResponseEntity<String> removerConta(@RequestHeader("Authorization") String Authorization, @RequestParam("email") String email, @RequestParam("senha") String senha) {
        if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
            return new ResponseEntity<>("Acesso Negado", HttpStatus.FORBIDDEN);
        }
        BasicResponse response = accountRegisterFacade.removerConta(email, senha);
        return new ResponseEntity<>(response.getMessage(), toHttpStatus(response.getStatus()));
    }
}
