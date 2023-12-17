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

    @PostMapping("/conta/cadastro")
    public ResponseEntity<String> efetuarCadastro(@RequestBody Conta conta) {
        return ResponseEntity.ok(fachada.efetuarCadastro(conta));
    }

    @GetMapping("/conta/exibir")
    public ResponseEntity<Conta> exibirConta(@RequestParam String email) {
        return ResponseEntity.ok(fachada.exibirConta(email));
    }
}
