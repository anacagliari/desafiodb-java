package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.repository.AluguelRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private LocatarioService locatarioService;

    @Autowired
    private LivroService livroService;

    public Aluguel salvaAluguel(Aluguel aluguel) {
        if (aluguel.getLocatario() == null || aluguel.getLocatario().getId() == null) {
            throw new RuntimeException("Locatário não informado.");
        }
        Optional<Locatario> locatarioExistente = locatarioService.buscaLocatarioPorId(aluguel.getLocatario().getId());
        if (!locatarioExistente.isPresent()) {
            throw new RuntimeException("Locatário não encontrado.");
        }

        if (aluguel.getLivros() == null || aluguel.getLivros().isEmpty()) {
            throw new RuntimeException("Livro(s) não informado(s).");
        }
        aluguel.getLivros().stream().forEach(livro -> {
            if (livro.getId() == null) {
                throw new RuntimeException("Livro não informado.");
            }
            Optional<Livro> livroExistente = livroService.buscaLivroPorId(livro.getId());
            if (!livroExistente.isPresent()) {
                throw new RuntimeException("Livro não encontrado. ID: " + livro.getId());
            }
        });

        if (aluguel.getDataRetirada() == null) {
            throw new RuntimeException("Data da retirada não informada.");
        }
        aluguel.setDataDevolucao(aluguel.getDataRetirada().plusDays(2L));

        return aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listaTodosAlugueis() {
        return aluguelRepository.findAll();
    }

    public Optional<Aluguel> buscaAluguelPorId(Long id) {
        return aluguelRepository.findById(id);
    }

}
