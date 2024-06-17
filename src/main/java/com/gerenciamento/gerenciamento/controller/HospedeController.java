package com.gerenciamento.gerenciamento.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gerenciamento.gerenciamento.entity.Hospede;
import com.gerenciamento.gerenciamento.service.HospedeService;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {
    private HospedeService hospedeService = null;

    @Autowired
    public void HospedesController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionarHospede() {
        ModelAndView mv = new ModelAndView("hospedes/cadastroHospede");
        return mv;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String, Object>> cadastrarHospede(@ModelAttribute("hospede") Hospede hospede) {
        Map<String, Object> response = new HashMap<>();
        try {
            hospedeService.cadastrarHospede(hospede);
            // Inserido com sucesso
            response.put("success", true);
            response.put("message", "Hóspede cadastrado com sucesso");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Erro ao cadastrar hóspede");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Map<String, Object>> atualizarHospede(@Validated @RequestBody Hospede hospede, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (result.hasErrors()) {
                response.put("success", false);
                response.put("message", "Erro de validação ao atualizar hóspede");
                return ResponseEntity.badRequest().body(response);
            }
    
            Optional<Hospede> hospedeExistenteOptional = hospedeService.buscarPorId(hospede.getId());
            if (hospedeExistenteOptional.isPresent()) {
                Hospede hospedeExistente = hospedeExistenteOptional.get();
                hospedeExistente.setNome(hospede.getNome());
                hospedeExistente.setEmail(hospede.getEmail());
                hospedeExistente.setTelefone(hospede.getTelefone());
                
                // Formatar CPF para o padrão 000.000.000-00 antes de setar
                String cpfFormatado = formatarCPF(hospede.getCpf());
                hospedeExistente.setCpf(cpfFormatado);
                
                hospedeService.atualizarHospede(hospedeExistente);
                response.put("message", "Hóspede atualizado com sucesso");
                response.put("success", true);
                return ResponseEntity.ok().body(response);
            } else {
                response.put("message", "Hóspede não encontrado");
                response.put("success", false);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Erro ao atualizar hóspede: " + e.getMessage());
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // Método utilitário para formatar CPF no formato 000.000.000-00
    private String formatarCPF(String cpf) {
        // Remove caracteres não numéricos do CPF
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        // Formata CPF para 000.000.000-00
        return String.format("%s.%s.%s-%s",
            cpfLimpo.substring(0, 3),
            cpfLimpo.substring(3, 6),
            cpfLimpo.substring(6, 9),
            cpfLimpo.substring(9, 11));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirHospede(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            hospedeService.excluirHospede(id); // Método para excluir o hóspede no serviço
            response.put("success", true);
            response.put("message", "Hóspede exluído com sucesso");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Erro ao excluir hóspede");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/listar")
    public ModelAndView listarHospedes() {
        List<Hospede> hospedes = hospedeService.listarHospedes();
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Hospede hospede : hospedes) {
            Map<String, Object> hospedeMap = new HashMap<>();
            hospedeMap.put("id", hospede.getId());
            hospedeMap.put("nome", hospede.getNome());
            hospedeMap.put("cpf", hospede.getCpf());
            hospedeMap.put("telefone", hospede.getTelefone());
            hospedeMap.put("email", hospede.getEmail());
            lista.add(hospedeMap);
        }
        ModelAndView mv = new ModelAndView("hospedes/listaHospede");
        mv.addObject("listaHospede", lista);
        return mv;
    }

    @GetMapping("/buscarHospedes")
    public List<Hospede> buscarHospedes(){
        return hospedeService.listarHospedes();
    }

    // @GetMapping("/{id}")
    // public Optional<Hospede> buscarPorId(@PathVariable Long id) {
    //     return hospedeService.buscarPorId(id);
    // }

    @GetMapping("/editar/{id}")
    public ModelAndView buscarPorId(@PathVariable("id") Long id) {
        Optional<Hospede> hospedeOptional = hospedeService.buscarPorId(id);
        if (hospedeOptional.isPresent()) {
            Hospede hospede = hospedeOptional.get();
            ModelAndView mv = new ModelAndView("hospedes/editarHospede");
            mv.addObject("hospede", hospede);
            return mv;
        } else {
            // Redirecionar para uma página de erro ou retornar uma mensagem de erro
            ModelAndView mv = new ModelAndView("error"); // Certifique-se de que a página "error" exista
            mv.addObject("message", "Hóspede não encontrado");
            return mv;
        }
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
