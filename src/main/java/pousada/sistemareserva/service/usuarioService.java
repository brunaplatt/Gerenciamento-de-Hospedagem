package pousada.sistemareserva.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pousada.sistemareserva.entity.usuario;
import pousada.sistemareserva.login.emailUtil;
import pousada.sistemareserva.login.tokenInvalidoException;
import pousada.sistemareserva.login.usuarioNotFoundException;
import pousada.sistemareserva.repository.usuarioRepository;

@Service
public class usuarioService {
    private final usuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public usuarioService(usuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public usuario cadastrarUsuario(usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public usuario atualizarUsuario(usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void criarUsuarioAdminPadrao() {
        usuario admin = new usuario();
        admin.setEmail("admin@quintaypua.com.br");
        admin.setSenha(passwordEncoder.encode("admin123@")); // Criptografa a senha antes de salvar
        usuarioRepository.save(admin);
    }

    public boolean autenticar(String email, String senha) {
        usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return passwordEncoder.matches(senha, usuario.getSenha());
        }
        return false;
    }

    private String gerarToken() {
        return UUID.randomUUID().toString();
    }

    private Date calcularDataExpiracao() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24); // Define a expiração do token para 24 horas a partir do momento atual
        return calendar.getTime();
    }

    private void enviarEmailRedefinicaoSenha(String email, String token) {
        try {
            emailUtil.enviarEmailRedefinicaoSenha(email, token);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail de redefinição de senha: " + e.getMessage());
        }
    }

    public void resetarSenha(String email) {
        usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            String token = gerarToken(); // Método para gerar um token único
            usuario.setTokenResetSenha(token);
            usuario.setDataExpiracaoTokenResetSenha(calcularDataExpiracao()); // Método para calcular a data de expiração do token
            usuarioRepository.save(usuario);
            enviarEmailRedefinicaoSenha(usuario.getEmail(), token); // Método para enviar um e-mail com o link de redefinição de senha
        } else {
            throw new usuarioNotFoundException("Usuário não encontrado para o e-mail fornecido.");
        }
    }

    public void redefinirSenha(String token, String novaSenha) {
        usuario usuario = usuarioRepository.findByTokenResetSenha(token);
        if (usuario != null && !usuario.isTokenExpirado()) {
            usuario.setSenha(passwordEncoder.encode(novaSenha)); // Criptografa a nova senha antes de salvar
            usuario.setTokenResetSenha(null); // Remove o token de redefinição de senha
            usuario.setDataExpiracaoTokenResetSenha(null); // Remove a data de expiração do token
            usuarioRepository.save(usuario);
        } else {
            throw new tokenInvalidoException("Token de redefinição de senha inválido ou expirado.");
        }
    }
}
    

