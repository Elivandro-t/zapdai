package com.dr7.dr7.application.services;

import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.domain.vo.cliente.*;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UsuarioService {
    UsuarioIntefaceRepository usuarioIntefaceRepository;
    public UsuarioService(UsuarioIntefaceRepository usuarioIntefaceRepository){
        this.usuarioIntefaceRepository = usuarioIntefaceRepository;

    }

    public void save(UsuarioRegistoDTO dto){

         usuarioIntefaceRepository.registroEmpresarial(dto);
    }
    public UsuarioRespostaCpfDTO findOneByCpf(String email){
        return usuarioIntefaceRepository.findByUsuarioByCpf(email);
    }

    public Token signin(AuthLoginDTO auth) {
       return usuarioIntefaceRepository.sigin(auth);

    }
    @Transactional
    public Mensagem codigoDeVerificacao(EnviaCode email) {
        return usuarioIntefaceRepository.codigoDeVerificacao(email);

    }
    @Transactional
    public msg VerificaCode(VerificaCode code){
        return usuarioIntefaceRepository.VerificaCode(code);
    }
    @Transactional
    public Mensagem alteraSenha(NewPassword pwd){
      return usuarioIntefaceRepository.alterPassows(pwd);
    }
    @Transactional
    public void avatar(Long idUsuario, MultipartFile file) throws IOException {
         usuarioIntefaceRepository.avatar( idUsuario, file);
    }
    public ResponseEntity<Resource> ExibeImagem(String imagem){
        Path path = Paths.get("avatar").resolve(imagem);
        try{
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists()||resource.isReadable()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }
            return  ResponseEntity.notFound().build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }
    @Transactional
    public void atualizaEndereco(Long idUsuario, EnderecoDTO enderecoDTO,String nome,String sexo,String numero) {
        usuarioIntefaceRepository.atualizaDadosEndereco(enderecoDTO,idUsuario,nome,sexo,numero);
    }
}
