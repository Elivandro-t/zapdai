package com.dr7.dr7.webService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pagamento")

public class PaymentReturnControler {

    @GetMapping("/sucesso")
    public ResponseEntity<String> pagamentoSucesso() {
        return ResponseEntity.ok("Pagamento aprovado com sucesso!");
    }

    @GetMapping("/falha")
    public ResponseEntity<String> pagamentoFalha() {
        return ResponseEntity.ok("Pagamento recusado ou falhou.");
    }

    @GetMapping("/pendente")
    public ResponseEntity<String> pagamentoPendente() {
        return ResponseEntity.ok("Pagamento está pendente de confirmação.");
    }
}
