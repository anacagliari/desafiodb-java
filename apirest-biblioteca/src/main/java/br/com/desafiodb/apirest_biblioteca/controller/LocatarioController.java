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

import br.com.desafiodb.apirest_biblioteca.model.Locatario;
import br.com.desafiodb.apirest_biblioteca.service.LocatarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/locatario")
public class LocatarioController {

    @Autowired
    private LocatarioService locatarioService;

    @PostMapping
    public ResponseEntity<Locatario> salvaLocatario(@Valid @RequestBody Locatario locatario) {
        Locatario locatarioSalvo = locatarioService.salvaLocatario(locatario);
        return ResponseEntity.ok(locatarioSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Locatario>> listaTodosLocatarios() {
        List<Locatario> locatarios = locatarioService.listaTodosLocatarios();
        return ResponseEntity.ok(locatarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locatario> buscaLocatarioPorId(@PathVariable Long id) {
        Optional<Locatario> locatario = locatarioService.buscaLocatarioPorId(id);
        return locatario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locatario> atualizaLocatario(@PathVariable Long id, @Valid @RequestBody Locatario locatarioDetalhes) {
        Optional<Locatario> locatarioExistente = locatarioService.buscaLocatarioPorId(id);
        if (!locatarioExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Locatario locatario = locatarioExistente.get();
        locatario.setNome(locatarioDetalhes.getNome());
        locatario.setSexo(locatarioDetalhes.getSexo());
        locatario.setTelefone(locatarioDetalhes.getTelefone());
        locatario.setEmail(locatarioDetalhes.getEmail());
        locatario.setDataNascimento(locatarioDetalhes.getDataNascimento());
        locatario.setCpf(locatarioDetalhes.getCpf());

        Locatario locatarioAtualizado = locatarioService.salvaLocatario(locatario);
        return ResponseEntity.ok(locatarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaLocatario(@PathVariable Long id) {
        Optional<Locatario> locatario = locatarioService.buscaLocatarioPorId(id);
        if (!locatario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        locatarioService.deletaLocatario(id);
        return ResponseEntity.noContent().build();
    }

}
