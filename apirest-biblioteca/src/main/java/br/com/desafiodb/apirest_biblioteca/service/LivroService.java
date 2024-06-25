package br.com.desafiodb.apirest_biblioteca.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.repository.LivroRepository;
import br.com.desafiodb.apirest_biblioteca.util.RegraNegocioException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;

    public Livro salvaLivro(Livro livro) {
        if(livro.getAutores() == null || livro.getAutores().isEmpty()) {
            throw new RegraNegocioException("Autor(es) não informado(s).");
        }
        Set<Autor> autoresConsultados = new HashSet<Autor>();
        for (Autor autor : livro.getAutores()) {
            Optional<Autor> autorExistente = this.autorService.buscaAutorPorId(autor.getId());
            if(!autorExistente.isPresent()){
                throw new RegraNegocioException("Autor não encontrado.");
            }
            autoresConsultados.add(autorExistente.get());
        }
        livro.setAutores(autoresConsultados);
        return livroRepository.save(livro);
    }

    public List<Livro> listaTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listaTodosLivrosDisponiveis() {
        return livroRepository.listaTodosLivrosDisponiveis(LocalDate.now());
    }

    public List<Livro> listaTodosLivrosAlugados() {
        return livroRepository.listaTodosLivrosAlugados(LocalDate.now());
    }

    public Optional<Livro> buscaLivroPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Livro alteraLivro(Livro livro) {
        Optional<Livro> livroExiste = livroRepository.findById(livro.getId());
        if (!livroExiste.isPresent()) {
            throw new RegraNegocioException("Livro não encontrado.");
        }
        Livro livroExistente = livroExiste.get();
        livroExistente.setNome(livro.getNome());
        livroExistente.setIsbn(livro.getIsbn());
        livroExistente.setDataPublicacao(livro.getDataPublicacao());
        Livro livroAtualizado = livroRepository.save(livroExistente);
        return livroAtualizado;
    }

    public void deletaLivro(Long id) {
        if (livroRepository.quantidadeAlugueisDoLivro(id) > 0) {
            throw new RegraNegocioException("Livro foi alugado, não pode ser deletado.");
        }
        livroRepository.deleteById(id);
    }

}
