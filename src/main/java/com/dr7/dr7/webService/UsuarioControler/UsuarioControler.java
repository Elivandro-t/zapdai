package com.dr7.dr7.webService.UsuarioControler;

import com.dr7.dr7.application.services.UsuarioService;
import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.vo.cliente.BuscaUsuario;
import com.dr7.dr7.domain.vo.cliente.UsuarioRegistoDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioResponseDTO;
import com.dr7.dr7.domain.vo.cliente.UsuarioRespostaCpfDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("zapdai/v1/usuario")
public class UsuarioControler {
    UsuarioService service;
    public UsuarioControler(UsuarioService usuarioService){
        this.service = usuarioService;
    }
    @PostMapping("/auth")
    public ResponseEntity<Token> signin(@RequestBody @Valid AuthLoginDTO auth){
        return ResponseEntity.ok().body(service.signin(auth));
    }

     @PostMapping("registro")
     @Transactional
    public ResponseEntity<UsuarioResponseDTO> SaveUser(@RequestBody @Valid UsuarioRegistoDTO usuarioRegistoDTO, UriComponentsBuilder builder){
        URI uri = builder.path("zapdai/v1/usuario/").build().toUri();
       var response = service.save(usuarioRegistoDTO);
       return ResponseEntity.created(uri).body(response);
    }
    @PostMapping("busca")
    public ResponseEntity<UsuarioRespostaCpfDTO> findOneUsuariobyCpf(@RequestBody @Valid BuscaUsuario usuario){
        var response = service.findOneByCpf(usuario.cpf());
        return ResponseEntity.ok().body(response);
    }
}
