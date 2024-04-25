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

    public List<Object[]> listarReservas() {
        return reservaRepository.listaReservas();
    }

    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id).get();
    }

    public List<Reserva> buscarPorResponsavel(long responsavel) {
        return reservaRepository.findByResponsavel(responsavel);
    }

    public List<Reserva> buscarPorDatas(Date checkIn, Date checkOut, Integer acomodacao){
        return reservaRepository.buscaPorDatas(checkIn, checkOut, acomodacao);
    }


    public List<Reserva> buscarPorDatasMenosUm(Date checkIn, Date checkOut, long id, Integer acomodacao){
        return reservaRepository.buscarPorDatasMenosUm(checkIn, checkOut, id, acomodacao);
    }
}
