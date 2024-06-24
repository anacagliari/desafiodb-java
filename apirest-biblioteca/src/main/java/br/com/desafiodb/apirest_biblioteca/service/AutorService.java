package br.com.desafiodb.apirest_biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor salvaAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> listaTodosAutores() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscaAutorPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Optional<Autor> buscaAutorPorNome(String nome) {
        return autorRepository.findByNome(nome);
    }

    public void deletaAutor(Long id) {
        if (autorRepository.quantidadeLivrosAssociados(id) > 0) {
            throw new RuntimeException("Autor possui livro(s) associado(s), não pode ser deletado.");
        }
        autorRepository.deleteById(id);
    }

}
