package com.gerenciamento.gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gerenciamento.gerenciamento.entity.Reserva;
import com.gerenciamento.gerenciamento.service.ReservaService;
import java.util.Map;

import java.util.HashMap;
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
    public HashMap<String, String> cadastrarReserva(@Validated Reserva reserva, BindingResult result) {
        HashMap<String, String> map = new HashMap<>();
        try {
            List<Reserva> reservasPorData = reservaService.buscarPorDatas(reserva.getCheckIn(), reserva.getCheckOut());
            String datasReservadas = "";
            for(Reserva t: reservasPorData) {
                //aqui você exibe os gets
                // a partir da variavel t
                datasReservadas += " "+t.getCheckIn() + " à "+t.getCheckOut();
            }
            if (datasReservadas.equals("")){
                reservaService.cadastrarReserva(reserva);
                map.put("Message", "Inserido com sucesso");
                map.put("success", "true");
                return map;
            }
            map.put("Message", "As datas: "+datasReservadas+" já estão reservadas");
            map.put("success", "false");
        } catch (Error e){
            map.put("Message", e.getMessage());
            map.put("success", "false");
        }
        return map;
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
    public List<Reserva> listarReservas() {
        List<Reserva> listaReserva = reservaService.listarReservas();
        return listaReserva;
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
