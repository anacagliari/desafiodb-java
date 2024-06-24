package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listaTodosLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscaLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public void deletaLivro(Long id) {
        livroRepository.deleteById(id);
    }
}
