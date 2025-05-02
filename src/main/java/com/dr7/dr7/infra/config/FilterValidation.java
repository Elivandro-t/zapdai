package com.dr7.dr7.infra.config;

import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class FilterValidation extends OncePerRequestFilter {
    UsuarioRepository repository;
    JWTService tokenservice;
    public FilterValidation(UsuarioRepository repository,JWTService service){
        this.repository = repository;
        this.tokenservice = service;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = ValidaToken(request);
        if (token != null) {

            var authservice = tokenservice.validaToken(token);
            UsuarioEntity usuario = repository.findOneByEmail(authservice);

            if (usuario != null) {

                var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("-> Requisição recebida em: " + request.getRequestURI());
                System.out.println("-> Token extraído: " + token);
                System.out.println("-> AuthService retornou: " + authservice);
                System.out.println("-> Usuario encontrado: " + usuario.getEmail());
                System.out.println("-> Autenticação no contexto: " + SecurityContextHolder.getContext().getAuthentication());
            } else {
                // Aqui o usuário não foi encontrado no banco de dados.
                System.out.println("Usuário não encontrado para o e-mail: " + authservice);
            }
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        filterChain.doFilter(request,response);

    }

    private String ValidaToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth!=null){
            return auth.replace("Bearer","").trim();
        }
        return null;
    }
}
