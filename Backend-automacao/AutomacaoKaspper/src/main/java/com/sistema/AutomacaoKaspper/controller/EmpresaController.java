package com.sistema.AutomacaoKaspper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistema.AutomacaoKaspper.model.Empresa;
import com.sistema.AutomacaoKaspper.repository.EmpresaRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public String listarEmpresas(Model model) {
        List<Empresa> empresas = empresaRepository.findAll();
        model.addAttribute("empresas", empresas);
        return "empresas/lista";
    }

    @GetMapping("/novo")
    public String novoEmpresaForm(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresas/formulario";
    }

    @PostMapping("/salvar")
    public String salvarEmpresa(@ModelAttribute Empresa empresa) {
        empresaRepository.save(empresa);
        return "redirect:/empresas";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpresa(@PathVariable Long id, Model model) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            model.addAttribute("empresa", empresa.get());
            return "empresas/formulario";
        } else {
            return "redirect:/empresas";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarEmpresa(@PathVariable Long id) {
        empresaRepository.deleteById(id);
        return "redirect:/empresas";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Empresa> listarEmpresasAPI() {
        return empresaRepository.findAll();
    }
}

