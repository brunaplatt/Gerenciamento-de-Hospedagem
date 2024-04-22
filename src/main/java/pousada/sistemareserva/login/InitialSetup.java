package pousada.sistemareserva.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import pousada.sistemareserva.service.usuarioService;

@Configuration
public class InitialSetup implements CommandLineRunner {

    private final usuarioService usuarioService;

    @Autowired
    public InitialSetup(usuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) throws Exception {
        usuarioService.criarUsuarioAdminPadrao();
    }
}