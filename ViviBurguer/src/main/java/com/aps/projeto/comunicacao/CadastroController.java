package com.aps.projeto.comunicacao;

import com.aps.projeto.mapper.ContaMapper;
import com.aps.projeto.negocio.Conta;
import com.aps.projeto.negocio.ContaRequest;
import com.aps.projeto.negocio.Fachada;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequiredArgsConstructor
public class CadastroController {

    private final Fachada fachada;
    private static final AtomicLong idCounter = new AtomicLong();

    @PostMapping("/conta/cadastro")
    public ResponseEntity<String> cadastrarConta(@RequestBody ContaRequest contaRequest) {
        Conta conta = ContaMapper.ContaRequestToConta(idCounter.getAndIncrement(), contaRequest);
        if (fachada.inserirConta(conta)) {
            return ResponseEntity.ok("Conta cadastrada");
        } else {
            return ResponseEntity.ok("Nao foi possivel cadastrar, essa conta ja existe");
        }

    }

    @GetMapping("/conta/exibir")
    public ResponseEntity<Conta> exibirConta(@RequestParam String email) {
        Conta conta = fachada.exibirConta(email);
        return ResponseEntity.ok(conta);
    }
}
