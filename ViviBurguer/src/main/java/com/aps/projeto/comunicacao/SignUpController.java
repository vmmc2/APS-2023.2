package com.aps.projeto.comunicacao;
import com.aps.projeto.negocio.Conta;
import com.aps.projeto.negocio.Fachada;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final Fachada fachada;

    @PostMapping("/conta/signUp")
    public String efetuarSignUp(@RequestBody Conta conta) {
        return fachada.efetuarSignUp(conta);
    }

    @GetMapping("/conta/get")
    public Conta exibirConta(@RequestParam String email) {
        return fachada.exibirConta(email);
    }
}
