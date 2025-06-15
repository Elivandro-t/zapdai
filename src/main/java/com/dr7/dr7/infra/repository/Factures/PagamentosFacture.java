package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.application.services.amqp.PagamentoDTo;
import com.dr7.dr7.application.services.amqp.StatusPagamento;
import com.dr7.dr7.gateways.PagamentoRespositoryInterface;
import com.dr7.dr7.infra.repository.repository.pagamento.Pagamento;
import com.dr7.dr7.infra.repository.repository.pagamento.PagamentoRespositoty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PagamentosFacture  implements PagamentoRespositoryInterface {
    PagamentoRespositoty respositoty;
    public  PagamentosFacture(PagamentoRespositoty respositoty){
        this.respositoty = respositoty;
    }


    @Override
    public void save(PagamentoDTo pagamento) {
     var pagamento1 = respositoty.findOneByEmail(pagamento.email());
       if(pagamento1.isPresent()){
           throw new RuntimeException("Usuário ainda está no período de teste gratuito");
//           if(pagamento.statusPago() && pagamento1.get().getStatusPagoPlano().equals(StatusPagamento.PAGO)){
//               throw  new RuntimeException("Plano ja incluido no usuario");
//           }
//           if (pagamento.planoName().equalsIgnoreCase("Zapdai Start") &&
//                   pagamento1.get().getDataFim() != null &&
//                   pagamento1.get().getDataFim().isAfter(LocalDateTime.now())) {
//               throw new RuntimeException("Usuário ainda está no período de teste gratuito");
//           }
       }
        respositoty.save(new Pagamento(pagamento));
    }
}
