package com.dr7.dr7.application.services.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentosListenerConsumer {

    @RabbitListener(queues = "pagamento.detalhes-pedido")
    public void RecebiMensagem(PagamentoDTo d){
       System.out.println(d.qrCodeLink());
    }
}
