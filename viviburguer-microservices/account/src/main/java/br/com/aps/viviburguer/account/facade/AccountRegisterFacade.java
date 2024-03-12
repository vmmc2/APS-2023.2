package br.com.aps.viviburguer.account.facade;

import br.com.viviburguer.common.negocio.entity.Conta;
import br.com.viviburguer.common.negocio.enumerators.Status;
import br.com.viviburguer.common.negocio.pojos.BasicResponse;
import br.com.viviburguer.common.negocio.pojos.SignInResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AccountRegisterFacade {
    private final RestTemplate restTemplate;

    @Value("${account.register.url}")
    private String accountRegisterURL;

    @Autowired
    public AccountRegisterFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BasicResponse efetuarSignUp(Conta conta) {
        ResponseEntity<Conta> responseEntity = restTemplate.postForEntity(
            accountRegisterURL + "/conta/cadastrarConta",
            new HttpEntity<>(conta),
            Conta.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return new BasicResponse("Conta cadastrada com sucesso!", Status.OK);
        } else if (responseEntity.getStatusCode().is4xxClientError()) {
            return new BasicResponse("Uma conta com os dados fornecidos já existe.", Status.BAD_REQUEST);
        } else {
            return new BasicResponse("Não foi possível cadastrar a conta.", Status.INTERNAL_ERROR);
        }
    }

    public SignInResponse efetuarSignIn(String email, String senha) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("email", email);
        requestBody.add("senha", senha);

        // Define headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create HttpEntity with parameters and headers
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request
        ResponseEntity<Conta> responseEntity = restTemplate.postForEntity(accountRegisterURL + "/conta/existeConta", requestEntity, Conta.class);


        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return new SignInResponse()
                    .setNome(responseEntity.getBody().getNome())
                    .setEmail(responseEntity.getBody().getEmail())
                    .setCpf(responseEntity.getBody().getCpf().getCpf())
                    .setStatus(HttpStatus.OK)
                    .setMessage("SignIn realizado com sucesso");
        } else if (responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
            return new SignInResponse()
                    .setNome("")
                    .setEmail("")
                    .setStatus(HttpStatus.NOT_FOUND)
                    .setMessage("Email não existe");
        } else {
            return new SignInResponse()
                    .setNome("")
                    .setEmail("")
                    .setStatus(HttpStatus.FORBIDDEN)
                    .setMessage("Email e senha não se correspodem");
        }
    }

    public BasicResponse removerConta(String email, String senha) {
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                accountRegisterURL + "/conta/removerConta",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Boolean.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return new BasicResponse("Conta removida com sucesso!", Status.OK);
        } else if (responseEntity.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
            return new BasicResponse("Conta não existe", Status.NOT_FOUND);
        } else {
            return new BasicResponse("Email e senha não correspodem", Status.FORBIDDEN);
        }
    }
}
