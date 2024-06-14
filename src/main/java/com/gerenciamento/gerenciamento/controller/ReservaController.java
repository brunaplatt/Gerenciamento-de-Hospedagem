package com.gerenciamento.gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.gerenciamento.entity.Reserva;
import com.gerenciamento.gerenciamento.service.ReservaService;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    // Douglas Pereira 09/06/2024 - Inicio
    // @GetMapping("/listar")
    // public ModelAndView listarReservas() {
    //     List<Object[]> resultados = reservaService.listarReservas();
    //     List<Object> lista = new ArrayList<>();
    //     for(Object[] obj : resultados) {
    //         Map<String, Object> reserva = new HashMap<>();
    //         reserva.put("id", obj[0]);
    //         reserva.put("nomeResponsavel", obj[10]);
    //         reserva.put("checkIn", obj[4]);
    //         reserva.put("checkOut", obj[5]);
    //         reserva.put("acomodacao", obj[1]);
    //         lista.add(reserva);
    //     }
    //     ModelAndView mv = new ModelAndView("reservas/listaReserva");
    //     mv.addObject("listaReserva", lista);
    //     return mv;
    // }

    @GetMapping("/listar")
    public ModelAndView listarReservas() {
        List<Object[]> resultados = reservaService.listarReservas();
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] obj : resultados) {
            if (obj.length < 5) {
                System.out.println("Aviso: Objeto reserva com menos de 5 elementos encontrado.");
                continue;
            }
            Map<String, Object> reserva = new HashMap<>();
            reserva.put("id", obj[0]);
            reserva.put("acomodacao", obj[1]);
            reserva.put("checkIn", obj[4]);
            reserva.put("checkOut", obj[5]);
            reserva.put("nomeResponsavel", obj[9]);
            lista.add(reserva);
        }
        ModelAndView mv = new ModelAndView("reservas/listaReserva");
        mv.addObject("listaReserva", lista);
        return mv;
    }
    // Douglas Pereira 09/06/2024 - Fim

    @GetMapping("/adicionar")
    public ModelAndView adicionar() {
        ModelAndView mv = new ModelAndView("reservas/cadastroReserva");
        return mv;
    }

    @PostMapping("/cadastrar")
    public HashMap<String, String> cadastrarReserva(@Validated Reserva reserva, BindingResult result) {
        HashMap<String, String> map = new HashMap<>();
        try {
            List<Reserva> reservasPorData = reservaService.buscarPorDatas(reserva.getCheckIn(), reserva.getCheckOut(), reserva.getAcomodacao());
            String datasReservadas = "";
            for(Reserva t: reservasPorData) {
                datasReservadas += " "+t.getCheckIn() + " à "+t.getCheckOut();
            }
            if (datasReservadas.equals("")){
                Date hoje = new Date();
                if (reserva.getCheckIn().after(reserva.getCheckOut())){
                    map.put("Message", "A data de entrada não pode ser maior que a de saída");
                    map.put("success", "false");
                    return map;
                } else if(hoje.after(reserva.getCheckIn())){
                    map.put("Message", "A data de entrada não pode ser maior hoje");
                    map.put("success", "false");
                    return map;
                }
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

    @GetMapping("/editar/{id}")
    public ModelAndView buscarPorId(@PathVariable("id") Long id) {
        Reserva reserva = reservaService.buscarPorId(id);
        ModelAndView mv = new ModelAndView("reservas/editarReserva");
        mv.addObject("reserva", reserva);
        return mv;
    }
    
    @PutMapping("/atualizar")
    public HashMap<String, String> atualizarReserva(@Validated Reserva reserva,BindingResult result) {
        HashMap<String, String> map = new HashMap<>();
        try {
            List<Reserva> reservasPorData = reservaService.buscarPorDatasMenosUm(reserva.getCheckIn(), reserva.getCheckOut(), reserva.getId(), reserva.getAcomodacao());
            String datasReservadas = "";
            for(Reserva t: reservasPorData) {
                datasReservadas += " "+t.getCheckIn() + " à "+t.getCheckOut();
            }
            if (datasReservadas.equals("")){
                Date hoje = new Date();
                if (reserva.getCheckIn().after(reserva.getCheckOut())){
                    map.put("Message", "A data de entrada não pode ser maior que a de saída");
                    map.put("success", "false");
                    return map;
                } else if(hoje.after(reserva.getCheckIn())){
                    map.put("Message", "A data de hoje não pode ser maior que a data de entrada");
                    map.put("success", "false");
                    return map;
                }
                reservaService.atualizarReserva(reserva);
                map.put("Message", "Alterado com sucesso");
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

    @DeleteMapping("/excluir/{id}")
    public HashMap<String, String> excluirReserva(@PathVariable Long id) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Reserva reserva = reservaService.buscarPorId(id);
            Date hoje = new Date();
            if (hoje.after(reserva.getCheckOut())){
                map.put("Message", "Não é possível excluir reservas que já foram utilizadas");
                map.put("success", "true");
            } else {
                reservaService.excluirReserva(id);
                map.put("Message", "Excluído com sucesso");
                map.put("success", "true");
            }
        } catch (Error e){
            map.put("Message", e.getMessage());
            map.put("success", "false");
        }
        return map;
    }
    
    @GetMapping("/buscarPorResponsavel/{responsavel}")
    public List<Reserva> buscarPorResponsavel(@PathVariable long responsavel) {
        return reservaService.buscarPorResponsavel(responsavel);
    }
}
