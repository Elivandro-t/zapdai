package com.dr7.dr7.infra.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.cliente.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JWTService {

    public String GeraToken(Usuario user, Collection<? extends GrantedAuthority> authorities){
       try {
           var algorith = Algorithm.HMAC256("fgdfsgdfgdsfgd");
           return JWT.create()
                   .withIssuer("API Zapidai")
                   .withSubject(user.getEmail())
                   .sign(algorith);
       }catch (JWTCreationException exception){
           throw new RuntimeException("Erro na geração de token!");
       }
    }

    public String validaToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("fgdfsgdfgdsfgd");
            return JWT.require(algorithm)
                    .withIssuer("API Zapidai")
                    .build()
                    .verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException(exception);
            // Invalid signature/claims
        }
    }
}
