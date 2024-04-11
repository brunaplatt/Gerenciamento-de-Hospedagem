package pousada.sistemareserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pousada.sistemareserva.entity.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Long> {
    usuario findByEmail(String email);

    usuario findByTokenResetSenha(String token);
}
