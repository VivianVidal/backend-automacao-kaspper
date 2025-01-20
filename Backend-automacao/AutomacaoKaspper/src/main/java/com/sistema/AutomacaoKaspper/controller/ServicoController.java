// ServicoController.java
package com.sistema.AutomacaoKaspper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistema.AutomacaoKaspper.model.Servico;
import com.sistema.AutomacaoKaspper.repository.ServicoRepository;
import com.sistema.AutomacaoKaspper.repository.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public String listarServicos(Model model) {
        List<Servico> servicos = servicoRepository.findAll();
        model.addAttribute("servicos", servicos);
        return "servicos/lista";
    }

    @GetMapping("/novo")
    public String novoServicoForm(Model model) {
        model.addAttribute("servico", new Servico());
        model.addAttribute("empresas", empresaRepository.findAll());
        return "servicos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarServico(@ModelAttribute Servico servico) {
        servicoRepository.save(servico);
        return "redirect:/servicos";
    }

    @GetMapping("/editar/{id}")
    public String editarServico(@PathVariable Long id, Model model) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()) {
            model.addAttribute("servico", servico.get());
            model.addAttribute("empresas", empresaRepository.findAll());
            return "servicos/formulario";
        } else {
            return "redirect:/servicos";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarServico(@PathVariable Long id) {
        servicoRepository.deleteById(id);
        return "redirect:/servicos";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Servico> listarServicosAPI() {
        return servicoRepository.findAll();
    }
}

