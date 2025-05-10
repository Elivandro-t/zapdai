package com.dr7.dr7.webService;

import com.dr7.dr7.application.emailService.EmailSendService;
import com.dr7.dr7.application.services.CardPaymentDTO;
import com.dr7.dr7.application.services.Form;
import com.dr7.dr7.application.services.PaymentResponseDTO;
import com.dr7.dr7.application.services.ProcessPayment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("process_payment")
public class ControlerPayment {
    @Autowired
    EmailSendService service;
    @Autowired
        private ProcessPayment cardPaymentService;
    @Autowired
    private Form form;

    @PostMapping("/v1/access_token")
        public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody @Valid CardPaymentDTO cardPaymentDTO, @RequestParam String token) {
            PaymentResponseDTO payment = cardPaymentService.processPayment(cardPaymentDTO,token);
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        }
    @GetMapping("/v2/access_token")
    public ResponseEntity<String> processPayment2(@RequestParam String token) {
        form.Gerar(token);
      return ResponseEntity.ok("enviado");
    }

    @PostMapping("/webhook/mercadopago")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        System.out.println("Notificação recebida: " + payload);
         service.emailSend("elivandro78@gmail.com",payload);
        return ResponseEntity.ok("Webhook recebido com sucesso!");
    }
}
