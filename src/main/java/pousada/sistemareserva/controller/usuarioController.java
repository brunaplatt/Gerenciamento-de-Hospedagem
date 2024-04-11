package pousada.sistemareserva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pousada.sistemareserva.entity.usuario;
import pousada.sistemareserva.service.usuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    private final usuarioService usuarioService;

    @Autowired
    public usuarioController(usuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public usuario cadastrarUsuario(@RequestBody usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario);
    }

    @PutMapping("/atualizar")
    public usuario atualizarUsuario(@RequestBody usuario usuario) {
        return usuarioService.atualizarUsuario(usuario);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
    }

    @GetMapping("/listar")
    public List<usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/buscarPorEmail/{email}")
    public usuario buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String email, @RequestParam String senha) {
        return usuarioService.autenticar(email, senha);
    }

    @PostMapping("/reset-senha")
    public void resetarSenha(@RequestParam String email) {
        usuarioService.resetarSenha(email);
    }
}

