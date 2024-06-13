package com.gerenciamento.gerenciamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gerenciamento.gerenciamento.entity.Hospede;
import com.gerenciamento.gerenciamento.service.HospedeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {
    private HospedeService hospedeService = null;

    @Autowired
    public void HospedesController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping("/cadastrar")
    public void cadastrarHospede(@RequestBody Hospede hospede) {
        hospedeService.cadastrarHospede(hospede);
    }

    @PutMapping("/atualizar")
    public void atualizarHospede(@RequestBody Hospede hospede) {
        hospedeService.atualizarHospede(hospede);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirHospede(@PathVariable Long id) {
        hospedeService.excluirHospede(id);
    }

    @GetMapping("/listar")
    public ModelAndView listarHospedes() {
        //return hospedeService.listarHospedes();
        ModelAndView mv = new ModelAndView("hospedes/listaHospede");
        return mv;
    }

    @GetMapping("/buscarHospedes")
    public List<Hospede> buscarHospedes(){
        return hospedeService.listarHospedes();
    }
    @GetMapping("/{id}")
    public Optional<Hospede> buscarPorId(@PathVariable Long id) {
        return hospedeService.buscarPorId(id);
    }

    @GetMapping("/buscarPorNome/{nome}")
    public List<Hospede> buscarPorNome(@PathVariable String nome) {
        return hospedeService.buscarPorNome(nome);
    }

    @GetMapping("/buscarPorCPF/{cpf}")
    public List<Hospede> buscarPorCPF(@PathVariable String cpf) {
        return hospedeService.buscarPorCPF(cpf);
    }
}
