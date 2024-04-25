package com.gerenciamento.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerenciamento.gerenciamento.entity.Reserva;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByResponsavel(long responsavel);

    @Query("Select k from Reserva k where (k.checkIn <= ?2 AND k.checkOut >= ?2) OR (k.checkIn <= ?1 AND k.checkOut >= ?1)")
    List<Reserva> buscaPorDatas(Date checkIn, Date checkOut);

    @Query(value = "Select r.*, h.nome AS responsavelNome from Reserva r INNER JOIN Hospede h ON h.id = r.responsavel", nativeQuery = true)
    List<Reserva> listaReservas();
}
