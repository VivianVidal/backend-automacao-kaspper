
package com.sistema.AutomacaoKaspper.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sistema.AutomacaoKaspper.model.Servico;
import com.sistema.AutomacaoKaspper.model.Tarefa;
import com.sistema.AutomacaoKaspper.repository.ServicoRepository;
import com.sistema.AutomacaoKaspper.repository.TarefaRepository;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicoRestController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        return servico.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}/tarefas")
    public ResponseEntity<List<Tarefa>> listarTarefasServico(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()) {
            List<Tarefa> tarefas = tarefaRepository.findByServicoId(id);
            return ResponseEntity.ok(tarefas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico novoServico = servicoRepository.save(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long id, @RequestBody Servico servico) {
        if (!servicoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        servico.setId(id);
        Servico servicoAtualizado = servicoRepository.save(servico);
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        if (!servicoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        servicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

