package com.dr7.dr7.infra.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dr7.dr7.domain.Auth.Perfil;
import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.empresas.Empresa;
import com.dr7.dr7.domain.vo.cliente.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JWTService {

    public String GeraToken(Usuario user, Collection<? extends GrantedAuthority> authorities){
       try {
           List<String> perfil = user.getRole().stream().map(Perfil::getName).toList();
           List<Empresa> empresas = user.getEmpresa() != null
                   ? user.getEmpresa().stream().map(Empresa::new).toList()
                   : List.of();
           Map<String,List<Empresa>> listMap = new HashMap<>();
           listMap.put("empresa",empresas);
           var algorith = Algorithm.HMAC256("fgdfsgdfgdsfgd");
           return JWT.create()
                   .withIssuer("API Zapidai")
                   .withClaim("usuarioId",user.getClientId())
                   .withSubject(user.getEmail())
                   .withClaim("username",user.getNome())
                   .withClaim("roles",perfil)
                   .withClaim("company",listMap)
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
