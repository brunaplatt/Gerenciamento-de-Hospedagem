package com.gerenciamento.gerenciamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.gerenciamento.entity.Reserva;
import com.gerenciamento.gerenciamento.repository.ReservaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void cadastrarReserva(Reserva hospede) {
        reservaRepository.save(hospede);
    }

    public void atualizarReserva(Reserva hospede) {
        reservaRepository.save(hospede);
    }

    public void excluirReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.listaReservas();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> buscarPorResponsavel(long responsavel) {
        return reservaRepository.findByResponsavel(responsavel);
    }

    public List<Reserva> buscarPorDatas(Date checkIn, Date checkOut){
        return reservaRepository.buscaPorDatas(checkIn, checkOut);
    }
}
