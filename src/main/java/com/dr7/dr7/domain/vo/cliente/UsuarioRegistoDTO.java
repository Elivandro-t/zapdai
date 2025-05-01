package com.dr7.dr7.domain.vo.cliente;

import com.dr7.dr7.domain.vo.EnderecoDTO;
import com.dr7.dr7.infra.repository.Entity.EnderecoEntity;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRegistoDTO(
       @NotBlank(message = "informe Nome") String nome,
       @NotBlank(message = "informe Numero")String phoneNumer,
       @NotBlank(message = "informe Cpf")String cpf,
       @NotBlank(message = "informe Data de nascimento") String dataNascimento,
       @NotBlank(message = "informe Sexo") String sexo,
       @NotBlank(message = "informe Email")String email,
       @NotBlank(message = "informe Senha") String password,
       EnderecoDTO endereco
) {
}
