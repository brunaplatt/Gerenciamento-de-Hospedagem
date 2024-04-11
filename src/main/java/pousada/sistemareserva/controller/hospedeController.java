package pousada.sistemareserva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pousada.sistemareserva.entity.hospede;
import pousada.sistemareserva.service.hospedeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospedes")
public class hospedeController {
    private hospedeService hospedeService = null;

    @Autowired
    public void HospedesController(hospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping("/cadastrar")
    public void cadastrarHospede(@RequestBody hospede hospede) {
        hospedeService.cadastrarHospede(hospede);
    }

    @PutMapping("/atualizar")
    public void atualizarHospede(@RequestBody hospede hospede) {
        hospedeService.atualizarHospede(hospede);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirHospede(@PathVariable Long id) {
        hospedeService.excluirHospede(id);
    }

    @GetMapping("/listar")
    public List<hospede> listarHospedes() {
        return hospedeService.listarHospedes();
    }

    @GetMapping("/{id}")
    public Optional<hospede> buscarPorId(@PathVariable Long id) {
        return hospedeService.buscarPorId(id);
    }

    @GetMapping("/buscarPorNome/{nome}")
    public List<hospede> buscarPorNome(@PathVariable String nome) {
        return hospedeService.buscarPorNome(nome);
    }

    @GetMapping("/buscarPorCPF/{cpf}")
    public List<hospede> buscarPorCPF(@PathVariable String cpf) {
        return hospedeService.buscarPorCPF(cpf);
    }
}
