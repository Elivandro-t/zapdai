package com.dr7.dr7.infra.repository.Factures;

import com.dr7.dr7.application.emailService.EmailSendService;
import com.dr7.dr7.domain.Auth.DTO.AuthLoginDTO;
import com.dr7.dr7.domain.Auth.DTO.Token;
import com.dr7.dr7.domain.Auth.Perfil;
import com.dr7.dr7.domain.Auth.Usuario;
import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.domain.vo.cliente.*;
import com.dr7.dr7.gateways.UsuarioIntefaceRepository;
import com.dr7.dr7.infra.config.JWTService;
import com.dr7.dr7.infra.repository.Entity.cliente.PerfilEntity;
import com.dr7.dr7.infra.repository.Entity.cliente.UsuarioEntity;
import com.dr7.dr7.infra.repository.repository.PerfilRespository;
import com.dr7.dr7.infra.repository.repository.UsuarioRepository;
import com.dr7.dr7.infra.validation.CodigoInvalidoExeption;
import com.dr7.dr7.infra.validation.Validators;
import jakarta.transaction.Transactional;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UsuarioFactures implements UsuarioIntefaceRepository {
    @Value("${avatar}")
    private String endpoint;
    UsuarioRepository usuarioRepository;
    AuthenticationManager authenticationManager;
    JWTService service;
    Validators validators;
    PerfilRespository perfilRespository;
    EmailSendService sendService;
    public  UsuarioFactures(
            UsuarioRepository usuarioRepository
            ,AuthenticationManager authenticationManager,
            JWTService service,
            Validators validators,
            PerfilRespository perfilRespository,
            EmailSendService emailSendService
    ){
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.validators = validators;
        this.perfilRespository = perfilRespository;
        this.sendService = emailSendService;
    }
    @Override
    public void registroEmpresarial(UsuarioRegistoDTO usuario) {
        PerfilEntity perfil = perfilRespository.findOnePerfilEntityByNameAndAtivoTrue("ROLE_USER").orElseThrow(()->new RuntimeException("Erro ao buscar perfil"));
            List<PerfilEntity> listaPerdil = new ArrayList<>();
            Optional usuarioEntity = usuarioRepository.findOneByEmailAndByCpfAndByFone(usuario.email(), usuario.cpf(), usuario.phoneNumer());
            if (usuarioEntity.isPresent()) {
                throw new RuntimeException("Usuario já existe");
            }
            validators.valid(usuario.password());

            Usuario usuarioModel = new Usuario(usuario);
            var usuraioEntity = new UsuarioEntity(usuarioModel);
            if (perfil != null) {
                listaPerdil.add(perfil);
            }
            usuraioEntity.setRoles(listaPerdil);
            usuarioRepository.save(usuraioEntity);

    }

    @Override
    public List<UsuarioResponseDTO> lista() {
        return List.of();
    }

    @Override
    public UsuarioRespostaCpfDTO findByUsuarioByCpf(String email) {
        var usuario = usuarioRepository.findOneByCpfEncontrado(email);
        if(usuario!=null){
            System.out.println("meu Endereco "+usuario.getEndereco().getCidade());
            var usuarioModel = new Usuario(usuario);
            return new UsuarioRespostaCpfDTO(usuarioModel);
        }
        throw new RuntimeException("Usuario não encontrado na base de dados!git ");
    }

    @Override
    public Token sigin(AuthLoginDTO login) {
        var token =new UsernamePasswordAuthenticationToken(login.email(),login.password());
        var entity =  authenticationManager.authenticate(token);

        var usuario = entity.getPrincipal();

        String tokenname = service.GeraToken(new Usuario((UsuarioEntity) usuario),entity.getAuthorities());
        return new Token(tokenname);
    }

    @Override
    public Mensagem codigoDeVerificacao(EnviaCode email) {
        validaEmail(email.email());
      UsuarioEntity usuario =  usuarioRepository.findOneByEmail(email.email());
      if(usuario!=null) {
          String code = randonCode();
          usuario.setExp(0);
          usuario.setVerifyCode(code);
          String msg = "Codigo de verificação!";
          var s = "Seu codigo de verificação!";
          sendService.emailSend(email.email(),msg,s,code);
          usuarioRepository.save(usuario);
          return new Mensagem("Codigo de verificação enviado ao email!");
      }
      throw new RuntimeException("Usuario não encontrado");
    }
    @Override
    public msg VerificaCode(VerificaCode code) {
        validaEmail(code.email());
        UsuarioEntity usuario = usuarioRepository.findOneByEmail(code.email());
        if(usuario!=null){
            if(!code.code().equals(usuario.getVerifyCode())){
                usuario.Verification();
                usuario.setAtivoCode(false);

                if(usuario.getExp()>=4){
                    throw new RuntimeException("Limite maximo de tentativas expirado!");
                }
                usuarioRepository.save(usuario);
                usuarioRepository.flush();
                return new Erro("Codigo de verificação invalido");
            }
            usuario.setExp(0);
            usuario.setAtivoCode(true);
            usuario.setVerifyCode("");
            usuarioRepository.save(usuario);
            return new Aviso("Sucess");
        }
        throw new RuntimeException("Usuario não encontrado");
    }

    @Override
    public Mensagem alterPassows(NewPassword pwd) {
        validators.valid(pwd.newPasswd().trim());
        UsuarioEntity usuario = usuarioRepository.findOneByEmail(pwd.email().trim());
        if(usuario!=null){
            var ativo = usuario.getAtivoCode()?true:false;
           if(ativo){
               usuario.setVerifyCode("");
               usuario.setAtivo(false);
               usuario.setAtivoCode(false);
               usuario.setExp(0);
               usuario.criptofrafiaDeSenha(pwd.newPasswd().trim());
               usuarioRepository.save(usuario);
               sendService.emailSend(pwd.email(),"Alteração de senha","","Sua senha foi alterada com sucesso!");
               return new Mensagem("Senha alterada com sucesso!");
           }
           usuario.setAtivoCode(false);
            usuarioRepository.save(usuario);
           throw new RuntimeException("Codigo de verificação não validado!");
        }
       throw new RuntimeException("Erro ao validar usuario!");
    }

    @Override
    public void avatar(Long idUsuario, MultipartFile imagem) throws IOException {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElseThrow(()->new RuntimeException("Id do Usuario invalido!"));
        byte[] b = imagem.getBytes();
        String pasta = "avatar";
        File file = new File(pasta);
        String name = usuario.getNome()+"_"+imagem.getOriginalFilename().replaceAll("\\s+", "_");
        String pah = pasta+"/"+validaImagem(pasta,name);
        if(!file.exists()){
            file.mkdirs();
        }
        if (usuario.getImg() != null && !usuario.getImg().isBlank()) {
            String caminhoAntigo = usuario.getImg().replace(endpoint, "");
            File imagemAntiga = new File(caminhoAntigo);
            if (imagemAntiga.exists()) {
                imagemAntiga.delete();
            }
        }

        Files.write(Paths.get(pah),b);
        String nameImagem =endpoint+pah;
        usuario.setImg(nameImagem);
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizaDadosEndereco(EnderecoDTO e,Long id,String nome,String sexo,String numero) {
        UsuarioEntity usuario = usuarioRepository.findOneById(id);
        if(usuario!=null){
            usuario.atualizaEndereco(e,nome,sexo,numero);
            usuarioRepository.save(usuario);
        }

    }

    private String validaImagem(String pasta, String nomeImagem) throws IOException {
        Path pathImagem = Paths.get(pasta, nomeImagem);
        if (!Files.exists(pathImagem)) {
            return nomeImagem;
        }
        String nome = nomeImagem;
        String ext = "";
        int idExt = nomeImagem.lastIndexOf(".");
        if(idExt>0){
            nome = nomeImagem.substring(0,idExt);
            ext = nomeImagem.substring(idExt);
        }
        int contador = 1;
        String novoNome;
        do {
           novoNome = String.format("%s(%d)%s",nome,contador,ext);
            pathImagem = Paths.get(pasta, novoNome);
            contador++;
        }while (Files.exists(pathImagem));
        return novoNome;

    }

    private String randonCode(){
       return  RandomStringUtils.randomAlphabetic(6).toUpperCase();
   }
    private void validaEmail(String email){
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new RuntimeException("Email invalido");
        }
    }

    /// //////////////////////////////////////////////////
}
