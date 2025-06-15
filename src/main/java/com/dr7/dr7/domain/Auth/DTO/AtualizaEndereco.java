package com.dr7.dr7.domain.Auth.DTO;

import com.dr7.dr7.domain.vo.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizaEndereco(@NotNull(message = "Id e obrigatorio")  Long id,@NotNull(message = "Informe o endereco") EnderecoDTO endereco,String nome,String sexo,String numero) {
}
