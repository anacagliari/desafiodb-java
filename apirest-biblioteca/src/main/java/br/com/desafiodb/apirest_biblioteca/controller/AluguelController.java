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

import br.com.desafiodb.apirest_biblioteca.model.Aluguel;
import br.com.desafiodb.apirest_biblioteca.service.AluguelService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping
    public ResponseEntity<Aluguel> salvaAluguel(@Valid @RequestBody Aluguel aluguel) {
        Aluguel aluguelSalvo = aluguelService.salvaAluguel(aluguel);
        return ResponseEntity.ok(aluguelSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> listaTodosAlugueis() {
        List<Aluguel> alugueis = aluguelService.listaTodosAlugueis();
        return ResponseEntity.ok(alugueis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> buscaAluguelPorId(@PathVariable Long id) {
        Optional<Aluguel> aluguel = aluguelService.buscaAluguelPorId(id);
        return aluguel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> atualizaAluguel(@PathVariable Long id, @Valid @RequestBody Aluguel aluguelDetalhes) {
        Optional<Aluguel> aluguelExistente = aluguelService.buscaAluguelPorId(id);
        if (!aluguelExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Aluguel aluguel = aluguelExistente.get();
        aluguel.setDataRetirada(aluguelDetalhes.getDataRetirada());
        aluguel.setDataDevolucao(aluguelDetalhes.getDataDevolucao());
        aluguel.setLocatario(aluguelDetalhes.getLocatario());
        aluguel.setLivros(aluguelDetalhes.getLivros());

        Aluguel aluguelAtualizado = aluguelService.salvaAluguel(aluguel);
        return ResponseEntity.ok(aluguelAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaAluguel(@PathVariable Long id) {
        Optional<Aluguel> aluguel = aluguelService.buscaAluguelPorId(id);
        if (!aluguel.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        aluguelService.deletaAluguel(id);
        return ResponseEntity.noContent().build();
    }
    
}
