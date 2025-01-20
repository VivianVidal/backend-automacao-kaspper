package com.sistema.AutomacaoKaspper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema.AutomacaoKaspper.model.Servico;
import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByEmpresaId(Long empresaId);
}

