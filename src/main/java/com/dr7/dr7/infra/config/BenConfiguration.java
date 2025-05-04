package com.dr7.dr7.infra.config;

import com.dr7.dr7.application.emailService.EmailSendService;
import com.dr7.dr7.application.services.EmpresaService;
import com.dr7.dr7.application.services.UsuarioService;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import com.dr7.dr7.infra.repository.Factures.EmpresaFactures;
import com.dr7.dr7.infra.repository.Factures.ProdutoFacture;
import com.dr7.dr7.infra.repository.Factures.UsuarioFactures;
import com.dr7.dr7.infra.repository.repository.PerfilRespository;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.repository.repository.EmpresaRepository;
import com.dr7.dr7.infra.repository.repository.ProdutosRepository;
import com.dr7.dr7.infra.validation.Validators;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class BenConfiguration {
    @Bean
    public EmpresaFactures empresaFactures(EmpresaRepository repository, UsuarioRepository clienteRepository){
        return new EmpresaFactures(repository,clienteRepository);
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

    @Bean
    public UsuarioFactures usuarioFactures( UsuarioRepository usuarioRepository,
                                            AuthenticationManager authenticationManager,
                                            JWTService service,
                                            Validators validators,
                                            PerfilRespository perfilRespository
                                            ){
        return new UsuarioFactures(usuarioRepository,authenticationManager,service,validators,perfilRespository);
    }
    @Bean
    public UsuarioService usuarioService(UsuarioIntefaceRepository usuarioIntefaceRepository){
        return new UsuarioService(usuarioIntefaceRepository);
    }
}
