package com.sistema.AutomacaoKaspper.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sistema.AutomacaoKaspper.model.Empresa;
import com.sistema.AutomacaoKaspper.model.Servico;
import com.sistema.AutomacaoKaspper.repository.EmpresaRepository;
import com.sistema.AutomacaoKaspper.repository.ServicoRepository;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaRestController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}/servicos")
    public ResponseEntity<List<Servico>> listarServicosEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            List<Servico> servicos = servicoRepository.findByEmpresaId(id);
            return ResponseEntity.ok(servicos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        if (!empresaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        empresa.setId(id);
        Empresa empresaAtualizada = empresaRepository.save(empresa);
        return ResponseEntity.ok(empresaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        if (!empresaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        empresaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}