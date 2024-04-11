package pousada.sistemareserva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pousada.sistemareserva.entity.hospede;
import pousada.sistemareserva.repository.hospedeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class hospedeService {
    private final hospedeRepository hospedeRepository;

    @Autowired
    public hospedeService(hospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public void cadastrarHospede(hospede hospede) {
        hospedeRepository.save(hospede);
    }

    public void atualizarHospede(hospede hospede) {
        hospedeRepository.save(hospede);
    }

    public void excluirHospede(Long id) {
        hospedeRepository.deleteById(id);
    }

    public List<hospede> listarHospedes() {
        return hospedeRepository.findAll();
    }

    public Optional<hospede> buscarPorId(Long id) {
        return hospedeRepository.findById(id);
    }

    public List<hospede> buscarPorNome(String nome) {
        return hospedeRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<hospede> buscarPorCPF(String cpf) {
        return hospedeRepository.findByCpf(cpf);
    }
}
