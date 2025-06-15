package com.dr7.dr7.application.services;

import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import com.dr7.dr7.infra.repository.repository.PerfilRespository;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.repository.repository.pagamento.Pagamento;
import com.dr7.dr7.infra.repository.repository.pagamento.PagamentoRespositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProcessaPagamento {
    private ExecutorService executorService = Executors.newFixedThreadPool(50);
    Integer process = 0;
    @Autowired
    PagamentoRespositoty pagamentoRespositoty;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    PerfilRespository perfilRespository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public void processaPagamento(){
        List<EmpresaEntity> empresa = empresaRepository.findAll();

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (EmpresaEntity p:empresa){
            futures.add(roda(p));
            process = empresa.size();
            if(empresa.size()< process){
                process = empresa.size();
            }
        }
        // espera todas as tarefas terminarem para poder excutar outra
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();



    }

    private CompletableFuture<Void> roda(EmpresaEntity p) {
       Pagamento pagamento =  pagamentoRespositoty.findOneByEmail(p.getEmail()).orElseThrow(()->new RuntimeException("Pagamento não encontrado"));
       if(pagamento.isStatusPago()){
          var per =  perfilRespository.findOnePerfilEntityByNameAndAtivoTrue("ROLE_ADMIN").orElseThrow(()->new RuntimeException("Perfil não encontrado"));
          var usuario = usuarioRepository.findOneByEmail(p.getEmail());


       }
       return null;
    }


}
