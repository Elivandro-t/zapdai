package com.dr7.dr7.infra.validation;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidation implements  Validators{
    @Override
    public void valid(String password){
        if(insegurePassword(password)){
            throw new RuntimeException("A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais");
        }
    }
    private static boolean insegurePassword(String str) {
        return str.length() < 8 || !containsNumber(str) || !containsLetter(str) || !containsSpecialCharacter(str);
    }

    private static boolean containsNumber(String str) {
        return str.matches(".*\\d.*");  // Verifica se tem pelo menos um número
    }

    private static boolean containsLetter(String str) {
        return str.matches(".*[a-zA-Z].*");  // Verifica se tem pelo menos uma letra
    }

    private static boolean containsSpecialCharacter(String str) {
        return str.matches(".*[!@#$%^&*(),.?\":{}|<>].*");  // Verifica se tem pelo menos um caractere especial
    }
    // vericacao de senha ao alterar
//    public User AlterarPassword(AlterPassword alterPassword) {
//        var usuario = usuarioRepository.getReferenceById(alterPassword.id());
//        if(!passwordEncoder.matches(alterPassword.password(),usuario.getPassword())){
//            throw  new RuntimeException("senha atual invalida!");
//        } if(!passwordEncoder.matches(alterPassword.newPassword(),usuario.getPassword())){
//            return usuario;
//        }
//        throw  new RuntimeException("senha nao podem ser iguais");
//    }
//    public User  Alterar(AlterPassword alterPassword) {
//        var usuario = usuarioRepository.findByEmail(alterPassword.email());
//        if(usuario==null){
//            throw new RuntimeException("usuario invalido");
//        }
//        if(!passwordEncoder.matches(alterPassword.password(),usuario.getPassword())){
//            throw  new RuntimeException("senha invalida");
//        } if(!passwordEncoder.matches(alterPassword.newPassword(),usuario.getPassword())){
//            return usuario;
//        }
//        throw  new RuntimeException("senha nao podem ser iguais");
//    }
}
