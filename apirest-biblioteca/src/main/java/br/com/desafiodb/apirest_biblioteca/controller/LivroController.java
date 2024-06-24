package br.com.desafiodb.apirest_biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> salvaLivro(@Valid @RequestBody Livro livro) {
        Livro livroSalvo = livroService.salvaLivro(livro);
        return ResponseEntity.ok(livroSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listaTodosLivros() {
        List<Livro> livros = livroService.listaTodosLivros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscaLivroPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscaLivroPorId(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @Valid @RequestBody Livro livroDetalhes) {
        Optional<Livro> livroExistente = livroService.buscaLivroPorId(id);
        if (!livroExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Livro livro = livroExistente.get();
        livro.setNome(livroDetalhes.getNome());
        livro.setIsbn(livroDetalhes.getIsbn());
        livro.setDataPublicacao(livroDetalhes.getDataPublicacao());
        livro.setAutores(livroDetalhes.getAutores());

        Livro livroAtualizado = livroService.salvaLivro(livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscaLivroPorId(id);
        if (!livro.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        livroService.deletaLivro(id);
        return ResponseEntity.noContent().build();
    }

}
