package pousada.sistemareserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pousada.sistemareserva.entity.hospede;

import java.util.List;

@Repository
public interface hospedeRepository extends JpaRepository<hospede, Long> {
    List<hospede> findByNomeContainingIgnoreCase(String nome);
    List<hospede> findByCpf(String cpf);
}
