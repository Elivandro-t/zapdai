package com.dr7.dr7.application.services.amqp;

import com.dr7.dr7.gateways.PagamentoRespositoryInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class PagamentosListenerConsumer {
    @Autowired
    private PagamentoRespositoryInterface pagamentoRespositoryInterface;
    ExecutorService executors = Executors.newFixedThreadPool(50);
    private final BlockingQueue<PagamentoDTo> fila = new LinkedBlockingQueue<>();
    @RabbitListener(queues = "pagamento.detalhes-pedido")
    public void RecebiMensagem(PagamentoDTo confirmePagemento){
        enfileirar(confirmePagemento);
    }

    @PostConstruct
    public void startWorker() {
        executors.submit(() -> {
            while (true) {
                PagamentoDTo dto = fila.take(); // espera até ter uma mensagem
                try {
                    processar(dto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void enfileirar(PagamentoDTo dto) {
        fila.offer(dto); // coloca na fila para processar depois na espera
    }
    private void processar(PagamentoDTo dto) {
        pagamentoRespositoryInterface.save(dto);
    }
}
