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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiodb.apirest_biblioteca.model.Autor;
import br.com.desafiodb.apirest_biblioteca.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping()
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor novoAutor = autorService.salvarAutor(autor);
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
        Autor autor = autorService.buscaAutorPorNome(nome).orElseThrow(() -> new RuntimeException("Autor não encontrado."));
        return ResponseEntity.ok(autor);
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
