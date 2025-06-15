package com.dr7.dr7.infra.repository.repository;

import com.dr7.dr7.domain.empresas.EmpresaComProdutoDTO;
import com.dr7.dr7.infra.repository.Entity.empresasEntity.EmpresaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity,String> {
    @Query("select p from EmpresaEntity p where p.cpfCnpj=:s or p.numeroDeTelefone=:telefone or email=:email")
    EmpresaEntity findOneByRazaoSocial( String s,String telefone,String email);

    @Query("select p from EmpresaEntity p where p.idEmpresa=:idEmpresa")
    Optional<EmpresaEntity> buscaEmpresaPorUsuario( String idEmpresa);

    @Query("select p from EmpresaEntity p left join fetch p.produtos ")
    List<EmpresaEntity> findAllEmpresas();
    @Query(value = """
    SELECT new com.dr7.dr7.domain.empresas.EmpresaComProdutoDTO(
        e.idEmpresa,
        e.nomeCompania,
        e.numeroDeTelefone,
        e.email,
        p.idProduto,
        p.producName,
        p.price,
        c.nome
    )
    FROM EmpresaEntity e
    JOIN e.produtos p ON p.idEmpresa = e.idEmpresa
    JOIN p.categoria c
    WHERE c.idCategoria = :idCategoria
""")
    Page<EmpresaComProdutoDTO> findAllEmpresasByCategoria(Long idCategoria, Pageable pageable);
}
