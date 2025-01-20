package com.sistema.AutomacaoKaspper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema.AutomacaoKaspper.model.Tarefa;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByServicoId(Long servicoId);
}