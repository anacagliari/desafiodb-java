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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.service.AutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping()
    public ResponseEntity<Autor> salvaAutor(@RequestBody Autor autor) {
        Autor novoAutor = autorService.salvaAutor(autor);
        return ResponseEntity.ok(novoAutor);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listaTodosAutores() {
        List<Autor> autores = autorService.listaTodosAutores();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscaAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.buscaAutorPorId(id).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/nome")
    public ResponseEntity<Autor> buscaAutorPorNome(@RequestParam String nome) {
        Autor autor = autorService.buscaAutorPorNome(nome)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));
        return ResponseEntity.ok(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @Valid @RequestBody Autor autorDetalhes) {
        Optional<Autor> autorExistente = autorService.buscaAutorPorId(id);
        if (!autorExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Autor autor = autorExistente.get();
        autor.setNome(autorDetalhes.getNome());
        autor.setSexo(autorDetalhes.getSexo());
        autor.setAnoNascimento(autorDetalhes.getAnoNascimento());
        autor.setCpf(autorDetalhes.getCpf());
        autor.setLivros(autorDetalhes.getLivros());

        Autor autorAtualizado = autorService.salvaAutor(autor);
        return ResponseEntity.ok(autorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaAutor(@PathVariable Long id) {
        Optional<Autor> autor = autorService.buscaAutorPorId(id);
        if (!autor.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletaAutor(id);
        return ResponseEntity.noContent().build();
    }

}
