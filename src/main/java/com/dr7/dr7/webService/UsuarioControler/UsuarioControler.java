package com.dr7.dr7.webService.UsuarioControler;

import com.dr7.dr7.application.services.UsuarioService;
import com.dr7.dr7.domain.Auth.DTO.AtualizaEndereco;
import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.domain.vo.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

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
    public ResponseEntity<Map<String,String>> SaveUser(@RequestBody @Valid UsuarioRegistoDTO usuarioRegistoDTO, UriComponentsBuilder builder){
        URI uri = builder.path("zapdai/v1/usuario/").build().toUri();
       service.save(usuarioRegistoDTO);
       return ResponseEntity.created(uri).body(Map.of("OK","Registrado com sucesso!"));
    }
    @PostMapping("search")
    public ResponseEntity<UsuarioRespostaCpfDTO> findOneUsuariobyCpf(@RequestBody @Valid BuscaUsuario usuario){
        var response = service.findOneByCpf(usuario.email());
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/code")
    public  ResponseEntity<Mensagem> envioDeCodigoVerificacao(@RequestBody @Valid EnviaCode email){
        Mensagem msg = service.codigoDeVerificacao(email);
        return ResponseEntity.ok().body(msg);
    }
    @PutMapping("/verification")
    public  ResponseEntity<msg> validaCodigoDeVarificacao(@RequestBody @Valid VerificaCode code){
        msg msg = service.VerificaCode(code);
        return ResponseEntity.ok().body(msg);
    }
    @PutMapping("/newpasswd")
    public  ResponseEntity<Mensagem> alterarSenhaEmailCodigo(@RequestBody @Valid NewPassword code){
        Mensagem msg = service.alteraSenha(code);
        return ResponseEntity.ok().body(msg);
    }

    @PutMapping("/avatar")
    public ResponseEntity<Map<String,String>> avatar(@RequestParam("id") Long idUsuario, @RequestParam("file") MultipartFile file) throws IOException {
       try {
           service.avatar(idUsuario,file);
           return ResponseEntity.ok().body(Map.of("msg","Atualizado com sucesso!"));
       }catch (RuntimeException e){
           throw new RuntimeException(e.getMessage());
       }
    }
    @GetMapping("/avatar/{avatar}")
    public ResponseEntity<Resource> findOndeAvatar(@PathVariable("avatar") String avatar){
        var res = service.ExibeImagem(avatar);
        return res;
    }
    @PutMapping("/address")
    public ResponseEntity<Map<String,String>> atualizaEndereco(@RequestBody AtualizaEndereco e){
        service.atualizaEndereco(e.id(),e.endereco(),e.nome(),e.sexo(),e.numero());
        return ResponseEntity.ok().body(Map.of("msg","Atualizado com sucesso"));
    }
}
