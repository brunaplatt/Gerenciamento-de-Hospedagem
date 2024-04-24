package com.gerenciamento.gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gerenciamento.gerenciamento.entity.Reserva;
import com.gerenciamento.gerenciamento.service.ReservaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private ReservaService reservaService = null;

    @Autowired
    public void ReservasController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionar() {
        ModelAndView mv = new ModelAndView("reservas/cadastroReserva");
        return mv;
    }

    @PostMapping("/cadastrar")
    public void cadastrarReserva(@RequestBody Reserva reserva) {
        reservaService.cadastrarReserva(reserva);
    }

    @PutMapping("/atualizar")
    public void atualizarReserva(@RequestBody Reserva reserva) {
        reservaService.atualizarReserva(reserva);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirReserva(@PathVariable Long id) {
        reservaService.excluirReserva(id);
    }

    @GetMapping("/listar")
    public ModelAndView listarReservas() {
        List<Reserva> listaReserva = reservaService.listarReservas();
        ModelAndView mv = new ModelAndView("reservas/listaReserva");
        mv.addObject("listaReserva", listaReserva);
        return mv;
    }

    @GetMapping("/{id}")
    public Optional<Reserva> buscarPorId(@PathVariable Long id) {
        return reservaService.buscarPorId(id);
    }

    @GetMapping("/buscarPorResponsavel/{responsavel}")
    public List<Reserva> buscarPorResponsavel(@PathVariable long responsavel) {
        return reservaService.buscarPorResponsavel(responsavel);
    }
}
