package com.dr7.dr7.infra.config;

import com.dr7.dr7.application.emailService.EmailSendService;
import com.dr7.dr7.application.services.EmpresaService;
import com.dr7.dr7.infra.repository.Factures.EmpresaFactures;
import com.dr7.dr7.infra.repository.Factures.ProdutoFacture;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import com.dr7.dr7.infra.repository.repository.ProdutosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class BenConfiguration {
    @Bean
    public EmpresaFactures empresaFactures(EmpresaRepository repository){
        return new EmpresaFactures(repository);
    }

    @Bean
    public EmpresaService empresaService(EmpresaFactures factures,EmailSendService emailSendService){
        return new EmpresaService(factures,emailSendService);
    }
    @Bean
    public EmpresaRepository repository(EmpresaRepository repository){
        return repository;
    }
    @Bean
    public EmailSendService emailSendService(JavaMailSender sender){
        return new EmailSendService(sender);
    }
    @Bean
    public ProdutoFacture produtoFacture(ProdutosRepository repository){
        return new ProdutoFacture(repository);
    }
}
