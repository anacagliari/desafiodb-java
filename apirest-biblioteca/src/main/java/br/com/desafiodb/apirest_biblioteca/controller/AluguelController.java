package br.com.desafiodb.apirest_biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
