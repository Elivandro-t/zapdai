package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.vo.CategoriaDTO;
import com.dr7.dr7.domain.vo.EmpresaDTO;
import com.dr7.dr7.domain.vo.EmpresaRespostaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CategoriaRepository {
     void save(CategoriaDTO categoria, MultipartFile image) throws IOException;
     List<CategoriaDTO> lista();
     void deleteCategoria(String nome) throws URISyntaxException;
}
