package com.gerenciamento.gerenciamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamento.gerenciamento.entity.Hospede;
import com.gerenciamento.gerenciamento.repository.HospedeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HospedeService {
    private final HospedeRepository hospedeRepository;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public void cadastrarHospede(Hospede hospede) {
        hospedeRepository.save(hospede);
    }

    public void atualizarHospede(Hospede hospede) {
        hospedeRepository.save(hospede);
    }

    public void excluirHospede(Long id) {
        hospedeRepository.deleteById(id);
    }

    public List<Hospede> listarHospedes() {
        return hospedeRepository.findAll();
    }

    public Optional<Hospede> buscarPorId(Long id) {
        return hospedeRepository.findById(id);
    }

    public List<Hospede> buscarPorNome(String nome) {
        return hospedeRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Hospede> buscarPorCPF(String cpf) {
        return hospedeRepository.findByCpf(cpf);
    }
}
