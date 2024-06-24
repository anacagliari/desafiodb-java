package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.repository.AluguelRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public Aluguel salvaAluguel(Aluguel aluguel) {
        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listaTodosAlugueis() {
        return aluguelRepository.findAll();
    }

    public Optional<Aluguel> buscaAluguelPorId(Long id) {
        return aluguelRepository.findById(id);
    }

    public void deletaAluguel(Long id) {
        aluguelRepository.deleteById(id);
    }
}
