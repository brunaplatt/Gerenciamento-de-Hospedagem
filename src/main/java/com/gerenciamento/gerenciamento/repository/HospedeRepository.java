// package com.gerenciamento.gerenciamento.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.gerenciamento.gerenciamento.entity.Hospede;

// import java.util.List;

// @Repository
// public interface HospedeRepository extends JpaRepository<Hospede, Long> {
//     List<Hospede> findByNomeContainingIgnoreCase(String nome);
//     List<Hospede> findByCpf(String cpf);
// }

package com.gerenciamento.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gerenciamento.gerenciamento.entity.Hospede;

import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    List<Hospede> findByNomeContainingIgnoreCase(String nome);
    List<Hospede> findByCpf(String cpf);

    @Query(value = "SELECT h.id, h.nome, h.cpf, h.telefone, h.email FROM Hospede h ORDER BY h.id DESC", nativeQuery = true)
    List<Object[]> listarHospedes();
}
