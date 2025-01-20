// TarefaController.java
package com.sistema.AutomacaoKaspper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistema.AutomacaoKaspper.model.Tarefa;
import com.sistema.AutomacaoKaspper.repository.TarefaRepository;
import com.sistema.AutomacaoKaspper.repository.ServicoRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public String listarTarefas(Model model) {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        model.addAttribute("tarefas", tarefas);
        return "tarefas/lista";
    }

    @GetMapping("/novo")
    public String novaTarefaForm(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        model.addAttribute("servicos", servicoRepository.findAll());
        return "tarefas/formulario";
    }

    @PostMapping("/salvar")
    public String salvarTarefa(@ModelAttribute Tarefa tarefa) {
        tarefaRepository.save(tarefa);
        return "redirect:/tarefas";
    }

    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable Long id, Model model) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            model.addAttribute("tarefa", tarefa.get());
            model.addAttribute("servicos", servicoRepository.findAll());
            return "tarefas/formulario";
        } else {
            return "redirect:/tarefas";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
        return "redirect:/tarefas";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Tarefa> listarTarefasAPI() {
        return tarefaRepository.findAll();
    }
}