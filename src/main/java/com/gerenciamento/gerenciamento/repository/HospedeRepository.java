package com.gerenciamento.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciamento.gerenciamento.entity.Hospede;

import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    List<Hospede> findByNomeContainingIgnoreCase(String nome);
    List<Hospede> findByCpf(String cpf);
}
