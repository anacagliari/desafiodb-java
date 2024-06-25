package br.com.desafiodb.apirest_biblioteca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.repository.AluguelRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

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
            throw new RegraNegocioException("Locatário não informado.");
        }
        Optional<Locatario> locatarioExistente = locatarioService.buscaLocatarioPorId(aluguel.getLocatario().getId());
        if (!locatarioExistente.isPresent()) {
            throw new RegraNegocioException("Locatário não encontrado.");
        }

        if (aluguel.getLivros() == null || aluguel.getLivros().isEmpty()) {
            throw new RegraNegocioException("Livro(s) não informado(s).");
        }
        aluguel.getLivros().stream().forEach(livro -> {
            if (livro.getId() == null) {
                throw new RegraNegocioException("Livro não informado.");
            }
            Optional<Livro> livroExistente = livroService.buscaLivroPorId(livro.getId());
            if (!livroExistente.isPresent()) {
                throw new RegraNegocioException("Livro não encontrado. ID: " + livro.getId());
            }
        });

        aluguel.setDataRetirada(LocalDate.now());
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
