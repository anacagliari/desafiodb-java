package br.com.desafiodb.apirest_biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.model.Livro;
import br.com.desafiodb.apirest_biblioteca.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> salvarLivro(@Valid @RequestBody Livro livro) {
        Livro livroSalvo = livroService.salvarLivro(livro);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroService.buscaLivroPorId(id);
        if (!livro.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        livroService.deletaLivro(id);
        return ResponseEntity.noContent().build();
    }

}
